package com.project.backend_api.repository;

import com.project.backend_api.models.Book;
import com.project.backend_api.models.Genre;
import com.project.backend_api.repositories.BookRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    Book book;


    @BeforeEach
    void setUp() {
        book = new Book("Catch 22", "0205080057123", LocalDate.parse("1961-10-11"), new Genre("Novel"), 12);
        bookRepository.save(book);
    }

    @AfterEach
    void tearDown() {
        book = null;
        bookRepository.deleteAll();
    }

    @Test
    void testFindByBookTitle_Found() {
        List<Book> bookList = bookRepository.findByTitle("Catch 22");
        assertThat(bookList.get(0).getId()).isEqualTo(book.getId());
        assertThat(bookList.get(0).getGenre()).isEqualTo(book.getGenre());
        assertThat(bookList.get(0).getAuthors()).isEqualTo(book.getAuthors());
    }

    @Test
    void testFindByBookTitle_NotFound() {
        List<Book> bookList = bookRepository.findByTitle("Harry Potter and the Philosopher's stone");
        assertThat(bookList.isEmpty()).isTrue();
    }

    @Test
    void testFindBookByIsbn_Found() {
        List<Book> bookList = bookRepository.findBookByIsbn("0205080057123").stream().toList();
        assertThat(bookList.get(0).getId()).isEqualTo(book.getId());
        assertThat(bookList.get(0).getGenre()).isEqualTo(book.getGenre());
        assertThat(bookList.get(0).getAuthors()).isEqualTo(book.getAuthors());
    }

    @Test
    void testFindBookByIsbn_NotFound() {
        List<Book> bookList = bookRepository.findBookByIsbn("0205080057124").stream().toList();
        assertThat(bookList.isEmpty()).isTrue();
    }

    //Success
    @Test
    void testFindBooksByGenreName_Found() {
        List<Book> bookList = bookRepository.findBooksByGenreName("Novel").stream().toList();
        assertThat(bookList.get(0).getId()).isEqualTo(book.getId());
        assertThat(bookList.get(0).getAuthors()).isEqualTo(book.getAuthors());
        assertThat(bookList.get(0).getGenre()).isEqualTo(book.getGenre());
    }

    ;

    @Test
    void testFindBooksByGenreName_NotFound() {
        List<Book> bookList = bookRepository.findBooksByGenreName("Fantasy").stream().toList();
        assertThat(bookList.isEmpty()).isTrue();
    }

    ;

}

