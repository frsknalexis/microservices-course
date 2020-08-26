package com.dev.microservices.core.users.processor;

import com.dev.microservices.core.users.model.request.AlumnoRequest;
import com.dev.microservices.core.users.model.entity.Alumno;
import com.dev.microservices.core.users.model.response.AlumnoResponse;
import com.dev.microservices.core.users.util.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlumnoProcessor {

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
        return alumnos.stream()
                .map(this::buildAlumnoResponse)
                .collect(Collectors.toList());
    }

    public Alumno buildAlumnoRequestToEntity(AlumnoRequest alumnoRequest) {
        return Alumno.builder()
                .nombre(alumnoRequest.getNombre())
                .apellido(alumnoRequest.getApellido())
                .email(alumnoRequest.getEmail())
                .fechaRegistro(DateUtils.stringToDate(alumnoRequest.getFechaRegistro()))
                .build();
    }
}