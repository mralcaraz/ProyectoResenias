package org.agrosoft.ProyectoResenias.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pelicula", uniqueConstraints = {@UniqueConstraint(columnNames = "id_tmdb")})
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula", columnDefinition = "INT UNSIGNED")
    private long idPelicula;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(name = "id_tmdb", nullable = false)
    private String tmdbId;

    @JsonIgnore
    @OneToMany(mappedBy = "peliculaReferida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resenia> resenias;
}