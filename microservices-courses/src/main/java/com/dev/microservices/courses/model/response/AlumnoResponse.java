package com.dev.microservices.courses.model.response;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class AlumnoResponse implements Serializable {

    private Integer alumnoId;

    private String nombre;

    private String apellido;

    private String email;

    private String fechaRegistro;
}