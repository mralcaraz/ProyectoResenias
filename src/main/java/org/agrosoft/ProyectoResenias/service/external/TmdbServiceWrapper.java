package org.agrosoft.ProyectoResenias.service.external;

import lombok.extern.slf4j.Slf4j;
import org.agrosoft.ProyectoResenias.dto.ApiResponseDto;
import org.agrosoft.ProyectoResenias.dto.TmdbMovieDto;
import org.agrosoft.ProyectoResenias.dto.TmdbMovieListDto;
import org.agrosoft.ProyectoResenias.exception.TmdbServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Slf4j
@Component
public class TmdbServiceWrapper {

    @Autowired
    private TmdbService client;

    public ApiResponseDto buscaDetallesPelicula(String tmdbId, String idioma) {
        ApiResponseDto apiResponse;
        String uri = UriComponentsBuilder.fromPath("/movie/{id}")
                .queryParam("language", idioma)
                .buildAndExpand(tmdbId)
                .toUriString();
        try {
            TmdbMovieDto clientResponse = client
                    .callGetEndpoint(uri, new ParameterizedTypeReference<TmdbMovieDto>() {})
                    .block();
            if(Objects.isNull(clientResponse)) {
                log.warn("No se encontraron resultados para {}", tmdbId);
                apiResponse = ApiResponseDto.builder()
                        .code(HttpStatus.NO_CONTENT.value())//204
                        .mensaje("La consulta fue correcta pero no hay resultados")
                        .build();
            } else {
                apiResponse = ApiResponseDto.builder()
                        .code(HttpStatus.OK.value())//200
                        .mensaje("Consulta exitosa")
                        .pelicula(clientResponse)
                        .build();
            }
        } catch (Exception e) {
            apiResponse = handleExceptions(e);
        }
        return apiResponse;
    }

    public ApiResponseDto buscaPorNombre(String nombre, String idioma, int pagina) {
        ApiResponseDto apiResponse;
        String uri = UriComponentsBuilder.fromPath("/search/movie")
                .queryParam("query", nombre)
                .queryParam("language", idioma)
                .queryParam("page", pagina)
                .build()
                .toUriString();
        try {
            TmdbMovieListDto clientResponse = client
                    .callGetEndpoint(uri, new ParameterizedTypeReference<TmdbMovieListDto>() {})
                    .block();
            if(Objects.isNull(clientResponse) || Objects.isNull(clientResponse.getListaPeliculas())) {
                log.warn("La consulta fue correcta, pero no hay resultados");
                apiResponse = ApiResponseDto.builder()
                        .code(HttpStatus.NO_CONTENT.value())
                        .mensaje("La consulta fue correcta pero no hay resultados")
                        .build();
            } else {
                log.info("Se encontraron {} peliculas en la pagina {}", clientResponse.getListaPeliculas().size(), pagina);
                apiResponse = ApiResponseDto.builder()
                        .code(HttpStatus.OK.value())
                        .mensaje("Consulta exitosa")
                        .listaPeliculas(clientResponse)
                        .build();
            }
        } catch (Exception e) {
            apiResponse = handleExceptions(e);
        }
        return apiResponse;
    }

    private ApiResponseDto handleExceptions(Exception e) {
        ApiResponseDto apiResponse;
        if(e.getCause() instanceof TmdbServiceException tse) {
            log.error("Error al llamar a TMDB: {}", e.getMessage());
            apiResponse = ApiResponseDto.builder()
                    .code(tse.getStatusCode().value())
                    .mensaje("Error al llamar a TMDB: " + tse.getStatusCode())
                    .build();
        } else {
            log.error("Error inesperado al consultar TMDB: {}", e.getMessage(), e);
            apiResponse = ApiResponseDto.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())//500
                    .mensaje("Error inesperado al consultar TMDB")
                    .build();
        }
        return apiResponse;
    }
}
