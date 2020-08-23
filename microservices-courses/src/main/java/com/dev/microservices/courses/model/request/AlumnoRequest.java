package com.dev.microservices.courses.model.request;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class AlumnoRequest implements Serializable {

    private Integer alumnoId;

    private String nombre;

    private String apellido;

    private String email;

    private String fechaRegistro;
}