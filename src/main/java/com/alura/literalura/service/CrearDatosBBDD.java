package com.alura.literalura.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.literalura.dto.DatosDTO;
import com.alura.literalura.entities.Autor;
import com.alura.literalura.entities.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;

import jakarta.transaction.Transactional;

@Component
public class CrearDatosBBDD {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LibroRepository libroRepository;
    private String URL_BASE = "https://gutendex.com/books/"; // URL base de la API
    private ConsumoAPI api = new ConsumoAPI(); // Clase que consume la API
    private ConvierteDatos conversor = new ConvierteDatos(); // Clase que convierte la respuesta de la API a un objeto

    public CrearDatosBBDD(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void cargarLibrosBBDD() {
        IntStream.range(1, 200).forEach(i -> {
            String json = api.obtenerDatos(URL_BASE + "?page=" + i);
            DatosDTO datos = conversor.obtenerDatos(json, DatosDTO.class);

            datos.libros().forEach(libro -> {
                Libro nuevoLibro = new Libro(libro);
                libro.autores().forEach(autor -> {
                    try {
                        Optional<Autor> autorBuscado = autorRepository.findByName(autor.name());
                        if (autorBuscado.isPresent()) {
                            nuevoLibro.setAutor(autorBuscado.get());
                            libroRepository.save(nuevoLibro);
                            return;
                        }
                        Autor a = new Autor(autor);
                        autorRepository.save(a);
                        nuevoLibro.setAutor(a);
                        libroRepository.save(nuevoLibro);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                });
            });
        });

    }

    @Transactional
    public void asignarValoresNulos() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(a -> {
            if (a.getBirth_year() == 0) {
                a.setBirth_year(0);
                a.setDeath_year(0);
            }
            if (a.getDeath_year() == 0) {
                a.setBirth_year(0);
                a.setDeath_year(0);
            }
        });

        autorRepository.saveAll(autores);
    }

}
