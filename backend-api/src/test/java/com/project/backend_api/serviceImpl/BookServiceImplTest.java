package com.project.backend_api.serviceImpl;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.mappers.BookDTOMapper;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.Genre;
import com.project.backend_api.repositories.BookRepository;
import com.project.backend_api.services.BookService;
import com.project.backend_api.services.implementation.BookServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    private BookService bookService;
    AutoCloseable autoCloseable;


    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    private  Book book;
    private BookDTO bookDTO;
    private BookDTOMapper bookDTOMapper;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository, bookDTOMapper);
        book = new Book("Catch 22","0205080057", LocalDate.parse("1961-10-11"),new Genre("Novel"),12);
        book.setId(32123L);


    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateBook() {
     mock(Book.class);
     mock(BookRepository.class);
     when(bookRepository.save(book)).thenReturn(book);
     assertThat(bookService.createBook(book)).isEqualTo("Book created successfully.");
    }

    @Test
    void testUpdateBook() {
        mock(Book.class);
        mock(BookRepository.class);
        when(bookRepository.save(book)).thenReturn(book);
        assertThat(bookService.updateBook(book)).isEqualTo("Book created successfully.");

    }
    @Test
    void testDeleteBook() {

    }
    @Test
    void testGetBook() {
        mock(Book.class);
        mock(BookRepository.class);
        when(bookRepository.findById(32123L)).thenReturn(Optional.ofNullable(book));
        assertThat(bookService.getBook(32123L).getTitle())
                .isEqualTo(book.getTitle());

    }
    @Test
    void testGetAllBooks() {
        mock(Book.class);
        mock(BookRepository.class);

        //mock to return book list of books
        when(bookRepository.findAll()).thenReturn(
                new ArrayList<Book>(Collections.singleton(book)));

        //mock to return book list of booksDTOs
    }
}


