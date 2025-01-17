package com.alura.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(
                @JsonAlias("id") Integer id,
                @JsonAlias("title") String titulo,
                @JsonAlias("languages") List<String> idiomas,
                @JsonAlias("download_count") Integer numeroDescargas,
                @JsonAlias("subjects") List<String> tags,
                @JsonAlias("authors") List<AutorDTO> autores) {

}
