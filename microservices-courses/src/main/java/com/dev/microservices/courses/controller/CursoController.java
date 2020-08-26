package com.dev.microservices.courses.controller;

import com.dev.microservices.courses.exception.NotFoundException;
import com.dev.microservices.courses.model.request.AlumnoRequest;
import com.dev.microservices.courses.model.request.CursoRequest;
import com.dev.microservices.courses.model.response.CursoResponse;
import com.dev.microservices.courses.payload.AlumnoPayload;
import com.dev.microservices.courses.payload.AlumnosPayload;
import com.dev.microservices.courses.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(@Autowired @Qualifier("cursoService") CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<CursoResponse>> findAll() throws NotFoundException {
        List<CursoResponse> cursosResponse = Optional.ofNullable(cursoService.findAll())
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontraron resultados");
                    return new NotFoundException(errorMessage);
                });

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cursosResponse);
    }

    @GetMapping(value = "/{cursoId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CursoResponse> findByCursoId(@PathVariable(value = "cursoId") Integer cursoId)
            throws NotFoundException {
        CursoResponse cursoResponse = Optional.ofNullable(cursoService.findById(cursoId))
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontro ningun curso con ID %s", cursoId);
                    return new NotFoundException(errorMessage);
                });

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cursoResponse);
    }

    @PostMapping(value = "/inserta", consumes = { MediaType.APPLICATION_JSON_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CursoResponse> crearCurso(@RequestBody CursoRequest cursoRequest) {
        CursoResponse cursoResponse = cursoService.save(cursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cursoResponse);
    }

    @PutMapping(value = "/modifica/{cursoId}", consumes = { MediaType.APPLICATION_JSON_VALUE },
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CursoResponse> modificarCurso(@RequestBody CursoRequest cursoRequest,
        @PathVariable(value = "cursoId") Integer cursoId) throws NotFoundException {

        if (!Optional.ofNullable(cursoService.findById(cursoId)).isPresent()) {
            String errorMessage = String.format("No se encontro ningun curso con ID %s", cursoId);
            throw new NotFoundException(errorMessage);
        }

        CursoResponse cursoResponse = cursoService.update(cursoRequest, cursoId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cursoResponse);
    }

    @DeleteMapping(value = "/elimina/{cursoId}")
    public ResponseEntity<?> eliminarCurso(@PathVariable(value = "cursoId") Integer cursoId)
            throws NotFoundException {
        CursoResponse cursoResponse = Optional.ofNullable(cursoService.findById(cursoId))
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontro ningun curso con ID %s", cursoId);
                    return new NotFoundException(errorMessage);
                });

        cursoService.deleteById(cursoResponse.getCursoId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{cursoId}/addAlumnos", produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CursoResponse> addAlumnoToCourse(@RequestBody AlumnosPayload alumnoRequests,
        @PathVariable(value = "cursoId") Integer cursoId) throws NotFoundException {
        CursoResponse cursoResponse = cursoService.addAlumnoToCourse(alumnoRequests.getAlumnos(), cursoId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cursoResponse);
    }

    @PutMapping(value = "/{cursoId}/removeAlumno")
    public ResponseEntity<CursoResponse> removeAlumnoToCourse(@RequestBody AlumnoPayload alumnoRequest,
        @PathVariable(value = "cursoId") Integer cursoId) throws NotFoundException {
        CursoResponse cursoResponse = cursoService.removeAlumnoToCourse(alumnoRequest.getAlumno(), cursoId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cursoResponse);
    }

    @GetMapping(value = "/alumno/{alumnoId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CursoResponse> findCursoByAlumnoId(@PathVariable(value = "alumnoId") Integer alumnoId)
        throws NotFoundException {
        CursoResponse cursoResponse = cursoService.findCursoByAlumnoId(alumnoId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cursoResponse);
    }
}