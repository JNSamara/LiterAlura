package com.alura.literalura.entities;

import java.util.List;

import com.alura.literalura.dto.LibroDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private List<String> idiomas;

    private Integer numeroDescargas;

    private List<String> tags;

    @ManyToOne
    private Autor autor;

    // CONSTRUCTORES
    public Libro() {

    }

    public Libro(LibroDTO libroDTO) {
        this.name = libroDTO.titulo();
        this.idiomas = libroDTO.idiomas();
        this.numeroDescargas = libroDTO.numeroDescargas();
        this.tags = libroDTO.tags();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Titulo -> " + this.getName() + "\nIdiomas -> " + this.getIdiomas() + "\nNumeroDescargas-> "
                + this.getNumeroDescargas()
                + "\nEtiquetas -> " + this.getTags() + "\nAutor -> " + autor.getname();
    }

}
