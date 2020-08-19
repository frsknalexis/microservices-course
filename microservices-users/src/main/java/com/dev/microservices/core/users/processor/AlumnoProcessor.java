package com.dev.microservices.core.users.processor;

import com.dev.microservices.core.users.model.dto.AlumnoDTO;
import com.dev.microservices.core.users.model.entity.Alumno;
import com.dev.microservices.core.users.util.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlumnoProcessor {

    public AlumnoDTO convertToAlumnoDTO(Alumno alumno) {
        return AlumnoDTO.builder()
                .alumnoId(alumno.getAlumnoId())
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellido())
                .email(alumno.getEmail())
                .fechaRegistro(DateUtils.dateToString(alumno.getFechaRegistro()))
                .build();
    }

    public List<AlumnoDTO> convertToListAlumnoDTO(List<Alumno> alumnos) {
        return alumnos.stream()
                .map(this::convertToAlumnoDTO)
                .collect(Collectors.toList());
    }

    public Alumno convertToAlumnoEntity(AlumnoDTO alumnoDTO) {
        return Alumno.builder()
                .alumnoId(alumnoDTO.getAlumnoId())
                .nombre(alumnoDTO.getNombre())
                .apellido(alumnoDTO.getApellido())
                .email(alumnoDTO.getEmail())
                .fechaRegistro(DateUtils.stringToDate(alumnoDTO.getFechaRegistro()))
                .build();
    }
}