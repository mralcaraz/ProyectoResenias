package org.agrosoft.ProyectoResenias.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class TMDBConfig {

    @Value("${tmdb.api.base.url}")
    private String baseUrl;

    @Value("${tmdb.api.token}")
    private String token;

    @Bean(name = "tmdbWebClient")
    public WebClient tmdbWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

}
