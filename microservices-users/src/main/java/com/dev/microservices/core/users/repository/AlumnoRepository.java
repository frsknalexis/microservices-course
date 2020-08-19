package com.dev.microservices.core.users.repository;

import com.dev.microservices.core.users.model.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("alumnoRepository")
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}