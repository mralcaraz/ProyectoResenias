package org.agrosoft.ProyectoResenias.repository;


import org.agrosoft.ProyectoResenias.entity.Resenia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReseniaDao extends JpaRepository<Resenia, Long> {
}
