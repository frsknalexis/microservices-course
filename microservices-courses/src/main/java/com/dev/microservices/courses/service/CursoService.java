package com.dev.microservices.courses.service;

import com.dev.microservices.courses.exception.NotFoundException;
import com.dev.microservices.courses.model.request.AlumnoRequest;
import com.dev.microservices.courses.model.request.CursoRequest;
import com.dev.microservices.courses.model.response.CursoResponse;

import java.util.List;

public interface CursoService {

    List<CursoResponse> findAll();

    CursoResponse findById(Integer cursoId);

    CursoResponse save(CursoRequest cursoRequest);

    CursoResponse update(CursoRequest cursoRequest, Integer cursoId);

    void deleteById(Integer cursoId);

    CursoResponse addAlumnoToCourse(List<AlumnoRequest> alumnoRequest, Integer cursoId)
       throws NotFoundException;

    CursoResponse removeAlumnoToCourse(AlumnoRequest alumnoRequest, Integer cursoId)
       throws NotFoundException;
}