package com.dev.microservices.core.users.service.impl;

import com.dev.microservices.core.users.model.request.AlumnoRequest;
import com.dev.microservices.core.users.model.entity.Alumno;
import com.dev.microservices.core.users.model.response.AlumnoResponse;
import com.dev.microservices.core.users.processor.AlumnoProcessor;
import com.dev.microservices.core.users.repository.AlumnoRepository;
import com.dev.microservices.core.users.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    private final AlumnoProcessor alumnoProcessor;

    public AlumnoServiceImpl(@Autowired @Qualifier("alumnoRepository") AlumnoRepository alumnoRepository,
        AlumnoProcessor alumnoProcessor) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoProcessor = alumnoProcessor;
    }

    @Override
    public List<AlumnoResponse> findAll() {
        return alumnoProcessor.buildListAlumnoResponse(alumnoRepository
                .findAll());
    }

    @Override
    public AlumnoResponse findById(Integer alumnoId) {
        return alumnoRepository.findById(alumnoId)
                .map(alumnoProcessor::buildAlumnoResponse)
                .orElse(null);
    }

    @Override
    public AlumnoResponse save(AlumnoRequest alumno) {
        Alumno alumnoEntity = alumnoProcessor.buildAlumnoRequestToEntity(alumno);
        alumnoEntity.setFechaRegistro(new Date());
        return alumnoProcessor.buildAlumnoResponse(alumnoRepository
                .save(alumnoEntity));
    }

    @Override
    public AlumnoResponse update(AlumnoRequest alumno, Integer alumnoId) {
        return alumnoRepository.findById(alumnoId)
                .map(alumnoEntity -> {
                    alumnoEntity.setNombre(alumno.getNombre());
                    alumnoEntity.setApellido(alumno.getApellido());
                    alumnoEntity.setEmail(alumno.getEmail());
                    return alumnoProcessor.buildAlumnoResponse(alumnoRepository
                            .save(alumnoEntity));
                })
                .orElse(null);
    }

    @Override
    public void deleteById(Integer alumnoId) {
        alumnoRepository.findById(alumnoId)
                .ifPresent(alumnoRepository::delete);
    }

    @Override
    public List<AlumnoResponse> findByNombreOrApellido(String termino) {
        return alumnoProcessor.buildListAlumnoResponse(alumnoRepository
                .findByNombreOrApellido(termino));
    }
}