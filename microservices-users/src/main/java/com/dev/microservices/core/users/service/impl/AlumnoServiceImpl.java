package com.dev.microservices.core.users.service.impl;

import com.dev.microservices.core.users.model.dto.AlumnoDTO;
import com.dev.microservices.core.users.model.entity.Alumno;
import com.dev.microservices.core.users.processor.AlumnoProcessor;
import com.dev.microservices.core.users.repository.AlumnoRepository;
import com.dev.microservices.core.users.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<AlumnoDTO> findAll() {
        return alumnoProcessor.convertToListAlumnoDTO(alumnoRepository
                .findAll());
    }

    @Override
    public AlumnoDTO findById(Integer alumnoId) {
        return alumnoRepository.findById(alumnoId)
                .map(alumnoProcessor::convertToAlumnoDTO)
                .orElse(null);
    }

    @Override
    public AlumnoDTO save(AlumnoDTO alumno) {
        Alumno alumnoEntity = alumnoProcessor.convertToAlumnoEntity(alumno);
        alumnoEntity.setFechaRegistro(new Date());
        return alumnoProcessor.convertToAlumnoDTO(alumnoRepository
                .save(alumnoEntity));
    }

    @Override
    public AlumnoDTO update(AlumnoDTO alumno, Integer alumnoId) {
        return alumnoRepository.findById(alumnoId)
                .map(alumnoEntity -> {
                    alumnoEntity.setNombre(alumno.getNombre());
                    alumnoEntity.setApellido(alumno.getApellido());
                    alumnoEntity.setEmail(alumno.getEmail());
                    return alumnoProcessor.convertToAlumnoDTO(alumnoRepository
                            .save(alumnoEntity));
                })
                .orElse(null);
    }

    @Override
    public void deleteById(Integer alumnoId) {
        alumnoRepository.findById(alumnoId)
                .ifPresent(alumnoRepository::delete);
    }
}