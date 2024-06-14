package com.ch4ll3ng3.alura.libreria.repositorio;

import com.ch4ll3ng3.alura.libreria.modelo.Autor;
import com.ch4ll3ng3.alura.libreria.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioLibro extends JpaRepository<Libro, Long> {

    @Query("SELECT DISTINCT a FROM Libro b JOIN b.autores a WHERE YEAR(a.añoNacer) <= :year")
    List<Autor> findByAuthorsBirthYearLessThanEqual(int year);

    @Query("SELECT b.authors FROM Libro b")
    List<Autor> findAllAuthors();

    @Query("SELECT a FROM Libro b JOIN b.autores a WHERE lower(a.name) LIKE lower(concat('%', :name, '%'))")
    List<Autor> findByAuthorsName(String name);


}
