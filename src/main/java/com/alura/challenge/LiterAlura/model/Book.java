package com.alura.challenge.LiterAlura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Author author;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language")
    private List<String> languages;
    private Integer downloadCount;

    public Book(){}
    public Book(BookData bookData){
        this.title = bookData.title();
        this.languages = bookData.languages();
        this.downloadCount = bookData.downloadCount();
        if(!bookData.authors().isEmpty()){
            this.author = new Author(bookData.authors().get(0));
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "---------LIBRO---------" + "\n" +
                "  Title='" + title + "',\n" +
                "  Author=" + author.getName() + ",\n" +
                "  Languages=" + languages + ",\n" +
                "  Download Count=" + downloadCount + "\n" +
                "-----------------------";
    }

}
