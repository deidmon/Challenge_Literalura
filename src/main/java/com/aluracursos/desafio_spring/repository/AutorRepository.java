package com.aluracursos.desafio_spring.repository;

import com.aluracursos.desafio_spring.Model.Autor;
import com.aluracursos.desafio_spring.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNombre(String nombre);

    List<Autor> findByFechaDeFallecimientoGreaterThanEqual(String anio);
}
