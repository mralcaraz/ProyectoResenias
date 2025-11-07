package org.agrosoft.ProyectoResenias.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonAlias("page")
    private int pagina;
    @JsonAlias("totalResults")
    private int resultadosTotales;
    @JsonAlias("totalPages")
    private int paginasTotales;
    @JsonAlias("results")
    private List<TmdbMovieDto> listaPeliculas;
}
