package org.agrosoft.ProyectoResenias.service.external;

import lombok.extern.slf4j.Slf4j;
import org.agrosoft.ProyectoResenias.exception.TmdbServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Service
public class TmdbService {

    @Autowired
    @Qualifier("tmdbWebClient")
    public WebClient tmdbWebClient;

    public <T> Mono<T> callGetEndpoint(String uri, ParameterizedTypeReference<T> responseType) {
        long start = System.currentTimeMillis();
        return tmdbWebClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> clientResponse
                            .bodyToMono(String.class)
                            .defaultIfEmpty("")
                            .flatMap(body -> {
                                log.error("Error al llamar al servicio {} -> {}", clientResponse.statusCode(), body);
                                return Mono.error(new TmdbServiceException(clientResponse.statusCode(), body));
                            })
                        )
                .bodyToMono(responseType)
                .retryWhen(Retry
                        .backoff(2, Duration.ofSeconds(2))
                        .filter(e -> {
                            if(e instanceof TmdbServiceException ex) {return ex.getStatusCode().is5xxServerError();}
                            return true;
                        }))
                .onErrorResume(e ->
                        Mono.error(new TmdbServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                        )
                .doFinally(signalType -> {
                    long elapsed = System.currentTimeMillis() - start;
                    log.info("GET completado en {} ms", elapsed);
                });
    }
}
