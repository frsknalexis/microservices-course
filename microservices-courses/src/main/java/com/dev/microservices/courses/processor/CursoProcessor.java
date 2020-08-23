package com.dev.microservices.courses.processor;

import com.dev.microservices.courses.entity.Alumno;
import com.dev.microservices.courses.entity.Curso;
import com.dev.microservices.courses.model.request.AlumnoRequest;
import com.dev.microservices.courses.model.request.CursoRequest;
import com.dev.microservices.courses.model.response.AlumnoResponse;
import com.dev.microservices.courses.model.response.CursoResponse;
import com.dev.microservices.courses.util.DateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CursoProcessor {

    public CursoResponse buildCursoResponse(Curso curso) {
        return CursoResponse.builder()
                .cursoId(curso.getCursoId())
                .nombre(curso.getNombre())
                .fechaRegistro(DateUtils.dateToString(curso.getFechaRegistro()))
                .alumnos(buildListAlumnoResponse(curso.getAlumnos()))
                .build();
    }

    public List<CursoResponse> buildListCursoResponse(List<Curso> cursos) {
        return cursos.stream()
                .map(this::buildCursoResponse)
                .collect(Collectors.toList());
    }

    public Curso buildCursoRequestToEntity(CursoRequest request) {
        return Curso.builder()
                .nombre(request.getNombre())
                .fechaRegistro(DateUtils.stringToDate(request.getFechaRegistro()))
                .alumnos(buildListAlumnoRequestToEntity(request.getAlumnos()))
                .build();
    }

    public Alumno buildAlumnoRequestToEntity(AlumnoRequest alumnoRequest) {
        return Alumno.builder()
                .alumnoId(alumnoRequest.getAlumnoId())
                .nombre(alumnoRequest.getNombre())
                .apellido(alumnoRequest.getApellido())
                .email(alumnoRequest.getEmail())
                .fechaRegistro(DateUtils.stringToDate(alumnoRequest.getFechaRegistro()))
                .build();
    }

    public List<Alumno> buildListAlumnoRequestToEntity(List<AlumnoRequest> alumnoRequests) {
        return Optional.ofNullable(alumnoRequests)
                .map(alumnos -> alumnos.stream()
                        .map(this::buildAlumnoRequestToEntity)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    public AlumnoResponse buildAlumnoResponse(Alumno alumno) {
        return AlumnoResponse.builder()
                .alumnoId(alumno.getAlumnoId())
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellido())
                .email(alumno.getEmail())
                .fechaRegistro(DateUtils.dateToString(alumno.getFechaRegistro()))
                .build();
    }

    public List<AlumnoResponse> buildListAlumnoResponse(List<Alumno> alumnos) {
        return Optional.ofNullable(alumnos)
                .map(a -> a.stream().
                        map(this::buildAlumnoResponse)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
}