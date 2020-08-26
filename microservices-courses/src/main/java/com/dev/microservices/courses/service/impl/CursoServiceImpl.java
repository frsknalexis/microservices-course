package com.dev.microservices.courses.service.impl;

import com.dev.microservices.courses.entity.Alumno;
import com.dev.microservices.courses.entity.Curso;
import com.dev.microservices.courses.exception.NotFoundException;
import com.dev.microservices.courses.model.request.AlumnoRequest;
import com.dev.microservices.courses.model.request.CursoRequest;
import com.dev.microservices.courses.model.response.CursoResponse;
import com.dev.microservices.courses.processor.CursoProcessor;
import com.dev.microservices.courses.repository.CursoRepository;
import com.dev.microservices.courses.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("cursoService")
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    private final CursoProcessor cursoProcessor;

    public CursoServiceImpl(@Autowired @Qualifier("cursoRepository") CursoRepository cursoRepository,
        @Autowired CursoProcessor cursoProcessor) {
        this.cursoRepository = cursoRepository;
        this.cursoProcessor = cursoProcessor;
    }

    @Override
    public List<CursoResponse> findAll() {
        return cursoProcessor.buildListCursoResponse(cursoRepository
                .findAll());
    }

    @Override
    public CursoResponse findById(Integer cursoId) {
        return cursoRepository.findById(cursoId)
                .map(cursoProcessor::buildCursoResponse)
                .orElse(null);
    }

    @Override
    public CursoResponse save(CursoRequest cursoRequest) {
        Curso cursoEntity = cursoProcessor.buildCursoRequestToEntity(cursoRequest);
        cursoEntity.setFechaRegistro(new Date());
        return cursoProcessor.buildCursoResponse(cursoRepository.save(cursoEntity));
    }

    @Override
    public CursoResponse update(CursoRequest cursoRequest, Integer cursoId) {
        return cursoRepository.findById(cursoId)
                .map(cursoEntity -> {
                    cursoEntity.setNombre(cursoRequest.getNombre());
                    return cursoProcessor.buildCursoResponse(cursoRepository
                            .save(cursoEntity));
                })
                .orElse(null);
    }

    @Override
    public void deleteById(Integer cursoId) {
        cursoRepository.findById(cursoId)
                .ifPresent(cursoRepository::delete);
    }

    @Override
    public CursoResponse addAlumnoToCourse(List<AlumnoRequest> alumnosRequest, Integer cursoId)
        throws NotFoundException {

        Curso cursoResponse = cursoRepository.findById(cursoId)
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontro ningun curso con ID %s", cursoId);
                    return new NotFoundException(errorMessage);
                });

        alumnosRequest.stream()
                .map(cursoProcessor::buildAlumnoRequestToEntity)
                .forEach(alumno -> cursoResponse.getAlumnos().add(alumno));

        return cursoProcessor.buildCursoResponse(cursoRepository.save(cursoResponse));
    }

    @Override
    public CursoResponse removeAlumnoToCourse(AlumnoRequest alumnoRequest, Integer cursoId)
        throws NotFoundException {
        Curso cursoResponse = cursoRepository.findById(cursoId)
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontro ningun curso con ID %s", cursoId);
                    return new NotFoundException(errorMessage);
                });

        List<Alumno> alumnosEntity = cursoResponse.getAlumnos().stream()
                .filter(alumno -> alumno.getAlumnoId() != null
                        && alumnoRequest.getAlumnoId() != null
                        && !alumno.getAlumnoId().equals(alumnoRequest.getAlumnoId()))
                .collect(Collectors.toList());

        cursoResponse.setAlumnos(alumnosEntity);
        return cursoProcessor.buildCursoResponse(cursoRepository.save(cursoResponse));
    }

    @Override
    public CursoResponse findCursoByAlumnoId(Integer alumnoId) throws NotFoundException {
        return Optional.ofNullable(cursoRepository.findCursoByAlumnoId(alumnoId))
                .map(cursoProcessor::buildCursoResponse)
                .orElseThrow(() -> {
                    String errorMessage = String.format("No se encontro ningun alumno con ID %s", alumnoId);
                   return new NotFoundException(errorMessage);
                });
    }
}