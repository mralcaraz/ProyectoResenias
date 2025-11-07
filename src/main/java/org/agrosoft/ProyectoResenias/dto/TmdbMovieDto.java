package org.agrosoft.ProyectoResenias.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("title")
    private String titulo;
    @JsonProperty("overview")
    private String descripcion;
    @JsonProperty("release_date")
    private String fechaEstreno;
    @JsonProperty("popularity")
    private double popularidad;
    @JsonProperty("vote_average")
    private double promedio;
    @JsonProperty("vote_count")
    private int conteoDeVotos;

}
