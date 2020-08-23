package com.dev.microservices.courses.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoResponse implements Serializable {

    private Integer cursoId;

    private String nombre;

    private String fechaRegistro;

    private List<AlumnoResponse> alumnos;
}