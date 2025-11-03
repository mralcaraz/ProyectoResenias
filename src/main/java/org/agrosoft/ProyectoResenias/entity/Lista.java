package org.agrosoft.ProyectoResenias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lista", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fk_usuario", "nombre_lista"})
})
public class Lista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista", columnDefinition = "INT UNSIGNED")
    private long idLista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre_lista", nullable = false, length = 50)
    private String nombreLista;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "peliculas_lista",
            joinColumns = @JoinColumn(name = "fk_lista", referencedColumnName = "id_lista", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_pelicula", referencedColumnName = "id_pelicula", nullable = false)
    )
    private List<Pelicula> peliculas;
}
