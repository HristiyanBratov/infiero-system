package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.mappers.BookDTOMapper;
import com.project.backend_api.models.*;
import com.project.backend_api.repositories.AuthorRepository;
import com.project.backend_api.repositories.BookRepository;
import com.project.backend_api.repositories.GenreRepository;
import com.project.backend_api.request.CreateBookRequest;
import com.project.backend_api.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, BookDTOMapper bookDTOMapper, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


    @Override
    public void createBook(CreateBookRequest request) {
        Genre genre = genreRepository.findById(request.getGenreId()).orElseThrow(() -> new IllegalArgumentException("Invalid genre ID"));

        Set<Author> authors = authorRepository.findAllById(request.getAuthorIds()).stream().collect(Collectors.toSet());

        if (authors.isEmpty()) {
            throw new IllegalArgumentException("At least one valid author is required");
        }


        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPublicationDate(request.getPublicationDate());
        book.setCopiesAvailable(request.getCopiesAvailable());
        book.setGenre(genre);
        book.setAuthors(authors);


        bookRepository.save(book);
    }


    @Override
    public void updateBook(Long bookId, CreateBookRequest request) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book with ID " + bookId + " not found"));

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }

        if (request.getIsbn() != null) {
            book.setIsbn(request.getIsbn());
        }

        if (request.getPublicationDate() != null) {
            book.setPublicationDate(request.getPublicationDate());
        }

        if (request.getCopiesAvailable() != null) {
            book.setCopiesAvailable(request.getCopiesAvailable());
        }


        if (request.getGenreId() != null) {
            Genre genre = genreRepository.findById(request.getGenreId()).orElseThrow(() -> new IllegalArgumentException("Invalid genre ID"));
            book.setGenre(genre);
        }


        if (request.getAuthorIds() != null && !request.getAuthorIds().isEmpty()) {
            Set<Author> authors = authorRepository.findAllById(request.getAuthorIds()).stream().collect(Collectors.toSet());

            if (authors.isEmpty()) {
                throw new IllegalArgumentException("At least one valid author is required");
            }

            book.setAuthors(authors);
        }


        bookRepository.save(book);
    }

    @Override
    public ResponseEntity<String> deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found to be deleted.");
        }

        bookRepository.deleteById(bookId);
        return ResponseEntity.ok("Book deleted successfully.");
    }

    @Override
    public BookDTO getBook(Long bookId) {
        return bookRepository.findById(bookId).map(bookDTOMapper).orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + " not found."));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(bookDTOMapper).collect(Collectors.toList());
    }
}