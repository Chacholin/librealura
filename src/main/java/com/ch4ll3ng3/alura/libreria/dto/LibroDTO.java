package com.ch4ll3ng3.alura.libreria.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Set;

public record LibroDTO(
        String titulo,
        Set<AutorDTO> autor,
        Set<String> lenguaje,
        @JsonAlias("download_count") Integer vecesDescargada) {
}
