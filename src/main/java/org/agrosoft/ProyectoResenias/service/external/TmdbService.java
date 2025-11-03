package org.agrosoft.ProyectoResenias.service.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TmdbService {

    @Autowired
    @Qualifier("tmdbWebClient")
    public WebClient tmdbWebClient;

    public String prueba() {
        String str = tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("language", "es-MX")
                        .queryParam("query", "Matrix")
                        .queryParam("page", "1")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(str);
        return str;
    }
}
