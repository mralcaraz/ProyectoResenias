package org.agrosoft.ProyectoResenias.repository;

import org.agrosoft.ProyectoResenias.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaDao extends JpaRepository <Pelicula, Long> {
}
