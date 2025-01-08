package com.project.backend_api.repository;

import com.project.backend_api.models.Book;
import com.project.backend_api.models.Genre;
import com.project.backend_api.repositories.BookRepository;
import com.project.backend_api.repositories.GenreRepository;
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
    private GenreRepository genreRepository;
    Book book;
    Genre genre;

    @BeforeEach
    void setUp() {
       genre.setName("Novel");
       genreRepository.save(genre);
        //    public Book(String title, String isbn, LocalDate publicationDate, Genre genre, Integer copiesAvailable) {LocalDate.parse("1961, 11, 10"),
        book = new Book("Catch 22","0205080057", LocalDate.parse("1961-10-11"),genre,12);
        bookRepository.save(book);
    }

    @AfterEach
    void tearDown() {
        genre = null;
        genreRepository.deleteAll();
        book = null;
        bookRepository.deleteAll();
    }


    //Success case
    @Test
    void testFindByBookTitle_Found() {
       List<Book> bookList = bookRepository.findByTitle("Catch 22");
       assertThat(bookList.get(0).getId()).isEqualTo(book.getId());
       assertThat(bookList.get(0).getTitle()).isEqualTo(book.getTitle());
    }

    //Failure case
    @Test
    void testFindByBookTitle_NotFound() {
        List<Book> bookList = bookRepository.findByTitle("Harry Potter and the Philosopher's stone");
        assertThat(bookList.isEmpty()).isTrue();
    }

}
