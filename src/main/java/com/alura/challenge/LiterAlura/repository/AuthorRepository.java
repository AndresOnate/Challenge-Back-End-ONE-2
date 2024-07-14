package com.alura.challenge.LiterAlura.repository;

import com.alura.challenge.LiterAlura.model.Author;
import com.alura.challenge.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByName(String name);

    List<Author> findByDeathYearIsNullOrDeathYearGreaterThanEqual(String year);

}