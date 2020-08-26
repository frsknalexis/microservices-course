package com.dev.microservices.examenes.model.response;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PreguntaResponse implements Serializable {

    private Integer preguntaId;

    private String texto;
}