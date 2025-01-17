# Literalura Application

LiterAlura es una aplicación de consola en Java exclusivamente desarrollada e impulsada por el challenge de Alura-Latam que permite gestionar y consultar información sobre libros y autores basados en la informacion proporcionada por la api de Gutendex.com. Utiliza Spring Boot y JPA para interactuar con la base de datos y realizar consultas.

## Funcionalidades

El menú principal de la aplicación ofrece las siguientes opciones:

1. **Buscar libro por título**: Permite buscar un libro ingresando su título.
2. **Listar todos los libros**: Muestra una lista de todos los libros en la base de datos.
3. **Lista de autores**: Muestra una lista de todos los autores en la base de datos.
4. **Listar autores vivos en determinado año**: Muestra una lista de autores que estaban vivos en un año específico.
5. **Cantidad de libros por autor**: Muestra la cantidad de libros escritos por un autor específico.
6. **Listar libros por idioma**: Muestra una lista de libros filtrados por idioma.
7. **Cantidad de libros de un idioma**: Muestra la cantidad de libros disponibles en un idioma específico.
8. **Buscar libro por etiqueta**: Permite buscar libros por una etiqueta específica.
9. **Top 5 libros más descargados**: Muestra una lista de los 5 libros más descargados.

0. **Salir**: Cierra la aplicación.

## Estructura del Proyecto

El proyecto está estructurado en las siguientes capas principales:

- **Entities**: Clases que representan las tablas de la base de datos.
- **DTOs**: Clases para transferir datos entre las capas de la aplicación.
- **Repositories**: Interfaces que extienden `JpaRepository` para interactuar con la base de datos.
- **Services**: Clases que contienen la lógica de negocio.
- **Controllers**: Clases que gestionan la entrada del usuario y llaman a los servicios apropiados.

## Requisitos

- Java 17 o superior
- Spring Boot 3.0.0 o superior
- Base de datos PostgreSQL

## Configuración del Proyecto

1. Clona el repositorio del proyecto:
   ```bash
   git clone https://github.com/DammiLopez/literalura-challenge.git
   
2. Configura la conexión a la base de datos en el archivo application.properties:
   ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
     spring.datasource.username=tu-usuario
     spring.datasource.password=tu-contraseña
     spring.jpa.hibernate.ddl-auto=update

##
