package com.project.backend_api.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "ISBN", nullable = false, unique = true, length = 13)
    private String isbn;

    private String genre;

    @Column(name = "Publication_Date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "Copiews_Available", nullable = false)
    private int copiesAvailable;

    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    public Book() {}

    public Book(String title, String isbn, LocalDate publicationDate, String genre, int copiesAvailable) {
        this.title = title;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.copiesAvailable = copiesAvailable;

        this.authors = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}