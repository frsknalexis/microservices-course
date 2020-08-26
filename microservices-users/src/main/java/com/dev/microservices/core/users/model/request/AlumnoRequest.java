package com.dev.microservices.core.users.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumnoRequest implements Serializable {

    private String nombre;

    private String apellido;

    private String email;

    private String fechaRegistro;
}