package com.dev.microservices.core.users.service;

import com.dev.microservices.core.users.model.dto.AlumnoDTO;
import com.dev.microservices.core.users.model.entity.Alumno;

import java.util.List;

public interface AlumnoService {

    List<AlumnoDTO> findAll();

    AlumnoDTO findById(Integer alumnoId);

    AlumnoDTO save(AlumnoDTO alumno);

    AlumnoDTO update(AlumnoDTO alumno);

    void deleteById(Integer alumnoId);
}