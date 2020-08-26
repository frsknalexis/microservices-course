package com.dev.microservices.core.users.service;

import com.dev.microservices.core.users.model.request.AlumnoRequest;
import com.dev.microservices.core.users.model.response.AlumnoResponse;

import java.util.List;

public interface AlumnoService {

    List<AlumnoResponse> findAll();

    AlumnoResponse findById(Integer alumnoId);

    AlumnoResponse save(AlumnoRequest alumno);

    AlumnoResponse update(AlumnoRequest alumno, Integer alumnoId);

    void deleteById(Integer alumnoId);

    List<AlumnoResponse> findByNombreOrApellido(String termino);
}