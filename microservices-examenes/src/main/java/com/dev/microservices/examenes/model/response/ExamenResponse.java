package com.dev.microservices.examenes.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ExamenResponse implements Serializable {

    private Integer examenId;

    private String nombre;

    private String fechaRegistro;

    private List<PreguntaResponse> preguntas;
}