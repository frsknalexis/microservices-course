package com.dev.microservices.core.users.repository;

import com.dev.microservices.core.users.model.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("alumnoRepository")
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("SELECT a FROM Alumno a WHERE a.nombre LIKE %:termino% " +
            "OR a.apellido LIKE %:termino%")
    List<Alumno> findByNombreOrApellido(@Param("termino") String termino);
}