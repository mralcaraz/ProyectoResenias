package org.agrosoft.ProyectoResenias.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovieDto {

    private long id;
    @JsonAlias("title")
    private String titulo;
    @JsonAlias("overview")
    private String descripcion;
    @JsonAlias("release_date")
    private String fechaEstreno;
    @JsonAlias("popularity")
    private double popularidad;
    @JsonAlias("vote_average")
    private double promedio;
    @JsonAlias("vote_count")
    private int conteoDeVotos;

}
