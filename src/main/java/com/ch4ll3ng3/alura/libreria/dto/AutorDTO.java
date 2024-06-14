package com.ch4ll3ng3.alura.libreria.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDTO(String nombre,
                       @JsonAlias("birth_year") String añoNacer,
                       @JsonAlias("death_year") String añoMuerte
) {
}
