package org.agrosoft.ProyectoResenias.repository;

import org.agrosoft.ProyectoResenias.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository <Usuario, Long>{
}
