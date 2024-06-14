package com.ch4ll3ng3.alura.libreria.principal;

import com.ch4ll3ng3.alura.libreria.dto.LibroDTO;
import com.ch4ll3ng3.alura.libreria.modelo.Autor;
import com.ch4ll3ng3.alura.libreria.modelo.Libro;
import com.ch4ll3ng3.alura.libreria.repositorio.RepositorioLibro;
import com.ch4ll3ng3.alura.libreria.servicio.ServivioGundex;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;


public class Principal {

    @Autowired
    private ServivioGundex servivioGundex;

    @Autowired
    private RepositorioLibro repositorioLibro;

          {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    findBookByTitle(scanner);
                    break;

                case 2:
                    listBooks();
                    break;

                case 3:
                    listAuthors();
                    break;

                case 4:
                    findAuthorByName(scanner);
                    break;

                case 5:
                    listAuthorsAliveByYear(scanner);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion No valida intente de nuevo");
            }
        }

        scanner.close();
    }

    public void menu() {
        System.out.println("1 - Buscar Libro Por titulo");
        System.out.println("2 - Lista de libros guardados");
        System.out.println("3 - Lista de autores Guardados");
        System.out.println("4 - Buscar autor por nombre");
        System.out.println("5 - Lista de autores vivos por año");
        System.out.println("0 - Salir");
        System.out.println("Digite una opcion");
    }

    private void findBookByTitle(Scanner scanner) {
        System.out.println("Escriba el nombre del titulo:");
        String titulo = scanner.nextLine();

        try {
            List<LibroDTO> libro = servivioGundex.searchBooks(titulo);

            if (libro.isEmpty()) {
                System.out.println("No hay ningun Libro");
            } else {
                for (int i = 0; i < libro.size(); i++) {
                    System.out.println(i + " - " + libro.get(i).titulo());
                }

                System.out.println("Escriba el nombre del libro que usted desea guardar:");
                int index = scanner.nextInt();
                scanner.nextLine();

                Libro libros = new Libro(libro.get(index));
                repositorioLibro.save(libros);
                System.out.println("Libro guardado con exito!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void listBooks() {
        List<Libro> libros = repositorioLibro.findAll();

        if (libros.isEmpty()) {
            System.out.println("Nombre del libro guardado");
        } else {
            System.out.println("Lista de libros guardados:");
            for (Libro libro : libros) {
                System.out.println("- " + libro.getTitulo());
            }
        }
    }

    public void listAuthors() {
        List<Autor> authors = repositorioLibro.findAllAuthors();

        if (authors.isEmpty()) {
            System.out.println("Nombres de autores guardados");
        } else {
            System.out.println("Lista de autores guardados:");
            for (Autor author : authors) {
                System.out.println("- " + author.getNombre());
            }
        }
    }

    public void findAuthorByName(Scanner scanner) {
        System.out.println("Escriba el nombre del autor:");
        String name = scanner.nextLine();

        List<Autor> authors = repositorioLibro.findByAuthorsName(name);

        if (authors.isEmpty()) {
            System.out.println("Numero de autores encontrados con ese nombre");
        } else {
            System.out.println("Autores encontrados:");
            for (Autor author : authors) {
                System.out.println("- " + author.getNombre());
            }
        }
    }

    public void listAuthorsAliveByYear(Scanner scanner) {
        System.out.println("Escriba el año:");
        int year = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = repositorioLibro.findByAuthorsBirthYearLessThanEqual(year);

        if (autores.isEmpty()) {
            System.out.println("Nombre de autor Encontrado Vivo: " + year);
        } else {
            System.out.println("Autores vivos en" + year + ":");
            for (Autor autor : autores) {
                System.out.println("- " + autor.getNombre());
            }
        }
    }

}
