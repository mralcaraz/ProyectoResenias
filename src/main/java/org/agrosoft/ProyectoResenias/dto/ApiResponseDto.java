package org.agrosoft.ProyectoResenias.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {
    private String mensaje;
    private int code;
    private TmdbMovieDto pelicula;
    private TmdbMovieListDto listaPeliculas;
}
