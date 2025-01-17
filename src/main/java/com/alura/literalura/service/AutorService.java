package com.alura.literalura.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literalura.entities.Autor;
import com.alura.literalura.repository.AutorRepository;

@Service
public class AutorService {

    Scanner teclado = new Scanner(System.in);

    @Autowired
    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void showAllAuthors() {
        autorRepository.findAll().forEach(autor -> System.out.println(autor.getname()));
    }

    public void showAllAuthorsAlive() {
        System.out.println("Introduce el año");
        Long year = teclado.nextLong();
        List<Autor> autores = autorRepository.autoresVivosPorAnio(year);
        autores.forEach(autor -> System.out.println(autor.getname()));

    }

}
