package com.dev.microservices.courses.repository;

import com.dev.microservices.courses.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("cursoRepository")
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Query("SELECT c FROM Curso c JOIN FETCH c.alumnos a WHERE a.alumnoId = :alumnoId")
    Curso findCursoByAlumnoId(@Param(value = "alumnoId") Integer alumnoId);
}