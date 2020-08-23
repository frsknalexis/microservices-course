package com.dev.microservices.courses.model.request;

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
public class CursoRequest implements Serializable {

    private String nombre;

    private String fechaRegistro;

    private List<AlumnoRequest> alumnos;
}