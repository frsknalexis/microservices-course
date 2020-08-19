package com.dev.microservices.core.users.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumnoDTO implements Serializable {

    private Integer alumnoId;

    private String nombre;

    private String apellido;

    private String email;

    private String fechaRegistro;
}