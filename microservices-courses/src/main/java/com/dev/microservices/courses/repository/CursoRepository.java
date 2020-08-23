package com.dev.microservices.courses.repository;

import com.dev.microservices.courses.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cursoRepository")
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}