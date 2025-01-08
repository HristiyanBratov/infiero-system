package com.project.backend_api.models;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "ISBN", nullable = false, unique = true, length = 13)

    private String isbn;

    @Column(name = "Publication_Date")
    private LocalDate publicationDate;

    @Column(name = "Copies_Available", nullable = false)

    private Integer copiesAvailable;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToMany(mappedBy = "books")
    private Set<LibraryBranch> libraryBranches;

    @OneToMany(mappedBy = "book")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "book")
    private Set<BookLoan> bookLoans;

    public Book() {}

    public Book(String title, String isbn, LocalDate publicationDate, Genre genre, Integer copiesAvailable) {
        this.title = title;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.copiesAvailable = copiesAvailable;

        this.authors = new HashSet<>();
        this.libraryBranches = new HashSet<>();
        this.reservations = new HashSet<>();
        this.bookLoans = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(Integer copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public void setBookLoans(Set<BookLoan> bookLoans) {
        this.bookLoans = bookLoans;
    }

    public Set<LibraryBranch> getLibraryBranches() {
        return libraryBranches;
    }

    public void setLibraryBranches(Set<LibraryBranch> libraryBranches) {
        this.libraryBranches = libraryBranches;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}