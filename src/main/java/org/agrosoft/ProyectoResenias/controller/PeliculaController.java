package org.agrosoft.ProyectoResenias.controller;

import org.agrosoft.ProyectoResenias.service.external.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tmdb")
public class PeliculaController {

    @Autowired
    private TmdbService service;

    @GetMapping("/search")
    public ResponseEntity<?> search() {
        String respuesta = service.prueba();
        return ResponseEntity.ok(respuesta);
    }
}
