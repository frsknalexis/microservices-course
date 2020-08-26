package com.dev.microservices.courses.payload;

import com.dev.microservices.courses.model.request.AlumnoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumnoPayload implements Serializable {

    private AlumnoRequest alumno;
}