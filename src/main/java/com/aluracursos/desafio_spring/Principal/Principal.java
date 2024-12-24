package com.aluracursos.desafio_spring.Principal;

import com.aluracursos.desafio_spring.Model.Autor;
import com.aluracursos.desafio_spring.Model.Datos;
import com.aluracursos.desafio_spring.Model.DatosLibro;
import com.aluracursos.desafio_spring.Model.Libro;
import com.aluracursos.desafio_spring.Service.ConsumoAPI;
import com.aluracursos.desafio_spring.Service.ConvierteDatos;
import com.aluracursos.desafio_spring.repository.AutorRepository;
import com.aluracursos.desafio_spring.repository.LibroRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository repositorioLibro, AutorRepository repositorioAutor) {
        this.libroRepositorio = repositorioLibro;
        this.autorRepositorio = repositorioAutor;
    }

    public void muestraElMenu () {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                        1 - Buscar libro por título 
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en un determinado año
                        5 - Listar libros por idioma
                        
                        0 - Salir
                        """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresBuscados();
                    break;
                case 4:
                    buscarAutoresVivosEnUnAnio();
                    break;
                case 5:
                    buscarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void buscarLibroWeb(){
        //Búsqueda de libros por nombre
        System.out.println("Ingresa el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        ConvierteDatos conversor = new ConvierteDatos();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()){
            DatosLibro datosLibro = libroBuscado.get();
            Autor autor = buscarAutor(datosLibro.autor().get(0).nombre());
            if (autor == null) {
                autor = new Autor();
                autor.setNombre(datosLibro.autor().get(0).nombre());
                autor.setFechaDeNacimiento(datosLibro.autor().get(0).fechaDeNacimiento());
                autor.setFechaDeFallecimiento(datosLibro.autor().get(0).fechaDeFallecimiento());
                autorRepositorio.save(autor);
            }
            Libro libro = buscarLibro(datosLibro.titulo());
            if (libro == null) {
                System.out.println(autor);
                libro = new Libro(datosLibro);
                libro.setAutor(autor);
                libroRepositorio.save(libro);
            }
            System.out.println(libro);

        } else {
            System.out.println("Libro no encontrado");
        }
    }
    private Autor buscarAutor(String nombreAutor) {
        return autorRepositorio.findByNombre(nombreAutor);
    }

    private Libro buscarLibro(String nombreTitulo) {
        return libroRepositorio.findByTitulo(nombreTitulo);
    }

    private void mostrarLibrosBuscados(){
        libros = libroRepositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro :: getTitulo))
                .forEach(System.out::println);
    }

    private void mostrarAutoresBuscados(){
        autores = autorRepositorio.findAll();

        autores.stream()
                .sorted(Comparator.comparing(Autor :: getNombre))
                .forEach(System.out::println);
    }

    private void buscarAutoresVivosEnUnAnio(){
        System.out.println("Ingresa el año que deseas buscar");
        String anio = teclado.nextLine();
        List<Autor> autoresVivos = autorRepositorio.findByFechaDeFallecimientoGreaterThanEqual(anio);
        autoresVivos.stream().forEach(System.out::println);
    }

    private void buscarLibrosPorIdioma(){
        System.out.println(
				"""
				Ingrese las siglas del idioma a buscar:
					1.- Español(es)
					2.- Ingles(en)
					3.- Frances(fr)
					4.- Portugues(pt)
				""");

        var idioma = teclado.nextLine();
        List<Libro> libros = libroRepositorio.findByIdiomas(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontró libros en ese idioma.");
        } else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
        }

    }

}
