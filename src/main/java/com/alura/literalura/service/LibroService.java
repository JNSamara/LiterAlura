package com.alura.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alura.literalura.entities.Libro;
import com.alura.literalura.repository.LibroRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

@Service
public class LibroService {

    private Scanner teclado = new Scanner(System.in);

    @Autowired
    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void mostrarLibrosPorIdioma() {
        List<Libro> libros = libroRepository.findAll();

        Map<String, List<Libro>> librosPorIdioma = new HashMap<>();

        for (Libro libro : libros) {
            for (String idioma : libro.getIdiomas()) {
                librosPorIdioma.computeIfAbsent(idioma, k -> new ArrayList<>()).add(libro);
            }
        }

        for (Map.Entry<String, List<Libro>> entry : librosPorIdioma.entrySet()) {
            System.out.println("Idioma: " + entry.getKey());
            for (Libro libro : entry.getValue()) {
                System.out.println("  Libro: " + libro.getName());
            }
        }
    }

    public void findByTitle() {
        System.out.println("Escribe el titulo del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        List<Libro> libroBuscado = libroRepository
                .findByNameIgnoreCase(nombreLibro);
        if (libroBuscado.isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }
        libroBuscado.stream().forEach(
                l -> {
                    System.out.println("--------------------------");
                    System.out.println(l);
                    System.out.println("--------------------------");
                });
    }

    public void showAllBooks() {

        System.out.println("--------------------------");
        libroRepository.findAll().forEach(libro -> {
            System.out.println(" - " + libro.getName());
        });
        System.out.println("--------------------------");
    }

    public void showBooksByLanguage() {

        System.out.println("Ingrese el idioma que desea buscar: ");
        String idiomaBuscado = teclado.nextLine().trim();

        List<Libro> libros = libroRepository.findAll();
        List<Libro> librosFiltrados = libros.stream()
                .filter(libro -> libro.getIdiomas().contains(idiomaBuscado))
                .collect(Collectors.toList());

        if (librosFiltrados.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idiomaBuscado);
        } else {
            System.out.println("Libros en el idioma " + idiomaBuscado + ":");
            for (Libro libro : librosFiltrados) {
                System.out.println("  Libro: " + libro.getName());
            }
        }
    }

    public void showAllBooksByTag() {
        System.out.println("Escribe la etiqueta que deseas buscar");
        String tag = teclado.nextLine().trim();

        List<Libro> libros = libroRepository.findAll();
        List<Libro> librosFiltrados = libros.stream()
                .filter(libro -> libro.getTags().contains(tag))
                .collect(Collectors.toList());

        if (librosFiltrados.isEmpty()) {
            System.out.println("No se encontraron libros con la etiqueta: " + tag);
        } else {
            System.out.println("Libros con la etiqueta " + tag + ":");
            for (Libro libro : librosFiltrados) {
                System.out.println("  Libro: " + libro.getName());
            }
        }
    }

    public void showTop5Books() {
        List<Libro> top5Libros = libroRepository.findTop5ByOrderByNumeroDescargasDesc(PageRequest.of(0, 5));

        top5Libros.forEach(libro -> System.out.println(libro.getName()));

    }

    public void countAmountOfBooksByLanguage() {
        System.out.println("Escribe el idioma que deseas buscar");
        String language = teclado.nextLine().trim();

        List<Libro> libros = libroRepository.findAll();
        List<Libro> librosFiltrados = libros.stream()
                .filter(libro -> libro.getIdiomas().contains(language))
                .collect(Collectors.toList());

        if (librosFiltrados.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + language);
            return;
        }
        System.out.println("El idioma " + language + " tiene " + librosFiltrados.size() + " libros.");

    }

    public void amountOfBooksByAuthor() {

        System.out.println("Escribe el nombre del autor que deseas buscar");
        String authorName = teclado.nextLine();

        Long amount = libroRepository.countBooksByAuthor(authorName);

        System.out.println("El autor " + authorName + " ha escrito " + amount + " libros.");
    }
}
