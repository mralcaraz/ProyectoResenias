package org.agrosoft.ProyectoResenias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pelicula", uniqueConstraints = {@UniqueConstraint(columnNames = "id_tmdb")})
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula", columnDefinition = "INT UNSIGNED")
    private long idPelicula;

    @Column
    private String nombre;

    @Column(name = "id_tmdb")
    private String tmdbId;


}