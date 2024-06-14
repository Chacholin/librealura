package com.ch4ll3ng3.alura.libreria.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")

public class Autor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String nombre;
        private LocalDate añoNacer;
        private LocalDate añoMuerte;

        @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private final Set<Libro> libros = new HashSet<>();

    public Autor() {
    }

    public Autor(String nombre, String añoNacer, String añoMuerte) {
        this.nombre = nombre;
        try {
            this.añoNacer = LocalDate.parse(añoNacer);
            this.añoMuerte = LocalDate.parse(añoMuerte);
        } catch (DateTimeParseException ex) {
            this.añoNacer = null;
            this.añoMuerte = null;
        }
    }

        @Override
        public String toString () {
        String librito= libros.stream().map(Libro::getTitulo).collect(Collectors.joining(", "));

        return "Author{" +
                "name='" + nombre + '\'' +
                ", birthYear=" + añoNacer +
                ", deathYear=" + añoMuerte +
                ", books=" + librito +
                '}';
    }

        public String getNombre () {
        return nombre;
    }

}