package com.alura.challenge.LiterAlura.repository;

import com.alura.challenge.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByLanguagesContaining(String language);
}
