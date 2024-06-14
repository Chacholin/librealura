package com.ch4ll3ng3.alura.libreria.modelo;

import com.ch4ll3ng3.alura.libreria.dto.LibroDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")

public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int vecesDescargadas;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Autor> autor = new HashSet<>();

    public Libro() {
    }

    public Libro(LibroDTO libroDTO) {
        this.titulo = libroDTO.titulo();
        this.vecesDescargadas = libroDTO.vecesDescargada();
        this.autor = libroDTO.autor().stream()
                .map(authorDTO -> new Autor(authorDTO.nombre(), authorDTO.añoNacer(), authorDTO.añoMuerte()))
                .collect(Collectors.toSet());
    }

    public String getTitulo() {
        return titulo;
    }

}
