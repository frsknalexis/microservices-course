package com.dev.microservices.examenes.repository;

import com.dev.microservices.examenes.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("examenRepository")
public interface ExamenRepository extends JpaRepository<Examen, Integer> {
}