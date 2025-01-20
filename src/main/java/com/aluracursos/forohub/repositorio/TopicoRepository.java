package com.aluracursos.forohub.repositorio;

import com.aluracursos.forohub.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Filtrar por curso y año.
    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoAndAnio(String curso, Integer anio, Pageable paginacion);

    // Filtrar por curso.
    Page<Topico> findByCurso(String curso, Pageable paginacion);
}
