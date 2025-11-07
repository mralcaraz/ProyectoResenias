package org.agrosoft.ProyectoResenias.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovieListDto {

    @JsonProperty("page")
    private int pagina;
    @JsonProperty("totalResults")
    private int resultadosTotales;
    @JsonProperty("totalPages")
    private int paginasTotales;
    @JsonProperty("results")
    private List<TmdbMovieDto> listaPeliculas;
}
