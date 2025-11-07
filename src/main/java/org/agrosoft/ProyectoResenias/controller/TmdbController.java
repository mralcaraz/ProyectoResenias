package org.agrosoft.ProyectoResenias.controller;

import lombok.extern.slf4j.Slf4j;
import org.agrosoft.ProyectoResenias.dto.ApiResponseDto;
import org.agrosoft.ProyectoResenias.service.external.TmdbServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tmdb")
public class TmdbController {

    @Autowired
    private TmdbServiceWrapper service;

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ApiResponseDto> buscarPorId(
            @PathVariable String id,
            @RequestParam(required = false, defaultValue = "es-MX") String idioma
    ){
        log.info("Iniciando busqueda por Id[{}], lenguaje[{}]", id, idioma);
        ApiResponseDto response = service.buscaDetallesPelicula(id, idioma);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<ApiResponseDto> buscar(
            @RequestParam String nombre,
            @RequestParam(required = false, defaultValue = "es-MX") String idioma,
            @RequestParam(required = false, defaultValue = "1") int page
    ){
        log.info("Iniciando busqueda por nombre[{}], pagina[{}]", nombre, page);
        ApiResponseDto response = service.buscaPorNombre(nombre, idioma, page);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}
