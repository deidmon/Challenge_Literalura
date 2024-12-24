# Proyecto Literalura

Este proyecto implementa una API para consultar libros y autores utilizando Java y Spring Boot, con PostgreSQL como base de datos para almacenar la información. Permite realizar diversas consultas a través de la consola, así como guardar los libros encontrados en la base de datos.📚


## Descripción del Proyecto 📖

El proyecto te permite realizar consultas sobre libros, autores y sus características a través de un menú interactivo en la consola. Además, puedes almacenar los libros consultados en una base de datos PostgreSQL.

### Funcionamiento del Programa

1. **Obtención de Datos**: El sistema permite realizar consultas sobre los libros registrados en la base de datos.
2. **Interacción con la Consola**: El usuario puede interactuar con el programa a través de un menú de opciones en la consola.
3. **Almacenamiento en la Base de Datos**: Los libros consultados se almacenan de manera persistente en una base de datos PostgreSQL.

### Características 🌟

- **Buscar libro por título**: Permite buscar libros registrados por su título.
- **Listar libros registrados**: Muestra todos los libros registrados en la base de datos.
- **Listar autores registrados**: Muestra todos los autores registrados en la base de datos.
- **Listar autores vivos en un determinado año**: Permite filtrar autores que estaban vivos en un año específico.
- **Listar libros por idioma**: Filtra los libros por el idioma seleccionado por el usuario.
- **Guardar libros consultados**: Los libros que se consultan y se visualizan son almacenados en la base de datos PostgreSQL.

## Tecnologías Utilizadas 🛠️

- **Java 17+**
- **Spring Boot 3.4.1**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Jackson (para manejo de JSON)**

## Instalación y Ejecución 🚀

Sigue estos pasos para configurar y ejecutar el proyecto en tu máquina local.

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior
- PostgreSQL o cualquier base de datos compatible


## Instalación

### 1. Clonar el Repositorio

Clona este proyecto desde GitHub utilizando el siguiente comando:

```bash
git clone https://github.com/usuario/proyecto-consulta-libros.git
```

### Configurar tu base de datos en el archivo application.properties
```bash
spring.application.name=desafio-spring
spring.datasource.url = jdbc:postgresql://${DB_HOST}/alura_libros
spring.datasource.username = ${DB_USER}
spring.datasource.password = ${DB_PASSWORD}
```


