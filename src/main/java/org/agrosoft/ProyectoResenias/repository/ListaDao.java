package org.agrosoft.ProyectoResenias.repository;

import org.agrosoft.ProyectoResenias.entity.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDao extends JpaRepository<Lista, Long> {
}
