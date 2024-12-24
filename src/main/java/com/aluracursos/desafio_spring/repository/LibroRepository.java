package com.aluracursos.desafio_spring.repository;

import com.aluracursos.desafio_spring.Model.Autor;
import com.aluracursos.desafio_spring.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Libro findByTitulo(String titulo);

    List<Libro> findByIdiomas(String idioma);

}
