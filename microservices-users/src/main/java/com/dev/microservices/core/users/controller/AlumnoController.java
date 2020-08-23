package com.dev.microservices.core.users.controller;

import com.dev.microservices.core.users.exception.NotFoundException;
import com.dev.microservices.core.users.model.dto.AlumnoDTO;
import com.dev.microservices.core.users.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(@Autowired @Qualifier("alumnoService") AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<AlumnoDTO>> findAll() throws NotFoundException {
        List<AlumnoDTO> alumnosResponse = Optional.ofNullable(alumnoService.findAll())
                .orElseThrow(() -> {
                    String errorMessage = "No se encontraron resultados";
                    return new NotFoundException(errorMessage);
                });

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(alumnosResponse);
    }

    @GetMapping(value = "/{alumnoId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<AlumnoDTO> findByAlumnoId(@PathVariable(value = "alumnoId") Integer alumnoId)
            throws NotFoundException {
        AlumnoDTO alumnoResponse = Optional.ofNullable(alumnoService.findById(alumnoId))
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontro ningun alumno con ID %s", alumnoId);
                    return new NotFoundException(errorMessage);
                });

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(alumnoResponse);
    }

    @PostMapping(value = "/inserta", consumes = { MediaType.APPLICATION_JSON_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<AlumnoDTO> crear(@RequestBody AlumnoDTO alumnoDTO) {
        AlumnoDTO alumnoResponse = alumnoService.save(alumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(alumnoResponse);
    }

    @PutMapping(value = "/modifica/{alumnoId}")
    public ResponseEntity<AlumnoDTO> modifica(@PathVariable(value = "alumnoId") Integer alumnoId,
        @RequestBody AlumnoDTO alumnoDTO) throws NotFoundException {

        AlumnoDTO alumno = alumnoService.findById(alumnoId);

        if (alumno == null) {
            String errorMessage = String.format("No se encontro ningun alumno con ID %s", alumnoId);
            throw new NotFoundException(errorMessage);
        }

        AlumnoDTO alumnoResponse = alumnoService.update(alumnoDTO, alumnoId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(alumnoResponse);
    }

    @DeleteMapping(value = "/elimina/{alumnoId}")
    public ResponseEntity<?> eliminaPorAlumnoId(@PathVariable(value = "alumnoId") Integer alumnoId)
            throws NotFoundException {
        AlumnoDTO alumnoResponse = Optional.ofNullable(alumnoService.findById(alumnoId))
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontro ningun alumno con ID %s", alumnoId);
                    return new NotFoundException(errorMessage);
                });

        alumnoService.deleteById(alumnoResponse.getAlumnoId());
        return ResponseEntity.noContent().build();
    }
}