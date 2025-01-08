package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.mappers.AuthorDTOMapper;
import com.project.backend_api.models.Author;
import com.project.backend_api.models.Book;
import com.project.backend_api.repositories.AuthorRepository;
import com.project.backend_api.repositories.BookRepository;
import com.project.backend_api.request.AuthorRequest;
import com.project.backend_api.services.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDTOMapper authorDTOMapper;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorDTOMapper authorDTOMapper, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.authorDTOMapper = authorDTOMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createAuthor(AuthorRequest request) {
        Set<Book> books = new HashSet<>(bookRepository.findAllById(request.getBookIds()));

        if (books.isEmpty()) {
            throw new IllegalArgumentException("At least one valid book is required");
        }

        Author author = new Author();
        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());
        author.setNationality(request.getNationality());
        author.setBooks(books);

        authorRepository.save(author);
    }

    @Override
    public void saveAuthor(Long authorId, AuthorRequest request) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author with ID " + authorId + " not found"));

        if (request.getFirstName() != null) {
            author.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            author.setLastName(request.getLastName());
        }

        if (request.getNationality() != null) {
            author.setNationality(request.getNationality());
        }

        if (request.getBookIds() != null && !request.getBookIds().isEmpty()) {
            Set<Book> books = new HashSet<>(bookRepository.findAllById(request.getBookIds()));

            if (books.isEmpty()) {
                throw new IllegalArgumentException("At least one valid book is required");
            }

            author.setBooks(books);
        }

        authorRepository.save(author);
    }

    @Override
    public AuthorDTO getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).map(authorDTOMapper).
                orElseThrow(() -> new EntityNotFoundException("Author with id: " + authorId + " not found."));
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> deleteAuthorById(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found for delete.");
        }

        authorRepository.deleteById(authorId);
        return ResponseEntity.ok("Author deleted successfully.");
    }
}