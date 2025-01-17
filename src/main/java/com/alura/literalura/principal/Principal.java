package com.alura.literalura.principal;

import java.util.Scanner;

import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.LibroService;

public class Principal {
    private LibroService libroService;
    private AutorService autorService;
    private Scanner teclado = new Scanner(System.in);

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.libroService = new LibroService(libroRepository);
        this.autorService = new AutorService(autorRepository);
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ------- MENU PRINCIPAL -------
                    1 - Buscar libro por titulo
                    2 - Listar todos los libros
                    3 - Lista de autores
                    4 - Listar autores vivos en determinado año
                    5 - Cantidad de libros por autor
                    6 - Listar libros por idioma
                    7 - Cantidad de libros de un idioma
                    8 - Buscar libro por etiqueta
                    9 - Top 5 libros mas descargados

                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    libroService.findByTitle();
                    break;
                case 2:
                    libroService.showAllBooks();
                    break;
                case 3:
                    autorService.showAllAuthors();
                    break;
                case 4:
                    autorService.showAllAuthorsAlive();
                    break;
                case 5:
                    libroService.amountOfBooksByAuthor();
                    break;
                case 6:
                    libroService.showBooksByLanguage();
                    break;
                case 7:
                    break;
                case 8:
                    libroService.showAllBooksByTag();
                    break;
                case 9:
                    libroService.showTop5Books();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

}
