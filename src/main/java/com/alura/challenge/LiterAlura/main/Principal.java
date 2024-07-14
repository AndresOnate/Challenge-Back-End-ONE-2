package com.alura.challenge.LiterAlura.main;

import com.alura.challenge.LiterAlura.model.Author;
import com.alura.challenge.LiterAlura.model.Book;
import com.alura.challenge.LiterAlura.model.BookData;
import com.alura.challenge.LiterAlura.repository.AuthorRepository;
import com.alura.challenge.LiterAlura.repository.BookRepository;
import com.alura.challenge.LiterAlura.service.ConsumoAPI;
import com.alura.challenge.LiterAlura.service.ConvierteDatos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {

    private Scanner scanner = new Scanner(System.in);

    private ConsumoAPI consumoApi = new ConsumoAPI();
    private static final String API_URL="https://gutendex.com/books/?search=";

    private ConvierteDatos conversor = new ConvierteDatos();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;


    public Principal(BookRepository repository, AuthorRepository authorRepository) {
        this.bookRepository = repository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    searchRegisteredBooks();
                    break;
                case 3:
                    searchRegisteredAuthors();
                    break;
                case 4:
                    searchAliveAuthorByYear();
                    break;
                case 5:
                    searchBooksByLanguages();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void searchBooksByLanguages() {
        System.out.println("Ingrese el idioma para buscar:");
        System.out.println("es - Español");
        System.out.println("en - Inglés");
        System.out.println("fr - Francés");
        System.out.println("pt - Portugués");
        var language = scanner.nextLine();
        List<Book> books = bookRepository.findByLanguagesContaining(language);
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma seleccionado.");
        } else {
            books.forEach(e -> System.out.println(e));
        }
    }

    private void searchAliveAuthorByYear() {
        System.out.println("Ingrese el año");
        var year = scanner.nextLine();
        List<Author> authorsAliveInYear = authorRepository.findByDeathYearIsNullOrDeathYearGreaterThanEqual(year);
        authorsAliveInYear.forEach(e -> System.out.println(e));
    }

    private void searchRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(e -> System.out.println(e));
    }

    private void searchRegisteredBooks() {
        List<Book> books = bookRepository.findAll();
        books.forEach(e -> System.out.println(e));
    }

    private void searchBookByTitle(){
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var title = scanner.nextLine();
        var json = consumoApi.getData(API_URL + title.replace(" ", "+"));
        try{
            BookData data = conversor.obtenerDatos(json, BookData.class);
            Book book = new Book(data);
            if(book.getAuthor() != null){
                Optional<Author> author = authorRepository.findByName(book.getAuthor().getName());
                if (author.isPresent()){
                    book.setAuthor(author.get());
                }else{
                    Author savedAuthor = authorRepository.save(book.getAuthor());
                    book.setAuthor(savedAuthor);
                }
            }
            bookRepository.save(book);
            System.out.println(book.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Libro no encontrado.");
        }
    }

    public String getBooks(){
        var json = consumoApi.getData(API_URL);
        BookData data = conversor.obtenerDatos(json, BookData.class);
        System.out.println(data);
        return json;
    }
}
