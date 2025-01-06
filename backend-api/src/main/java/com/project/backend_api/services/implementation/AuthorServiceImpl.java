package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.mappers.AuthorDTOMapper;
import com.project.backend_api.models.Author;
import com.project.backend_api.repositories.AuthorRepository;
import com.project.backend_api.services.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDTOMapper authorDTOMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorDTOMapper authorDTOMapper) {
        this.authorRepository = authorRepository;
        this.authorDTOMapper = authorDTOMapper;
    }

    @Override
    public ResponseEntity<String> createAuthor(Author author) {
        authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author created successfully.");
    }

    @Override
    public ResponseEntity<String> saveAuthor(Author author) {
        if(!authorRepository.existsById(author.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found for update.");
        }

        authorRepository.save(author);
        return ResponseEntity.ok("Author updated successfully.");
    }

    @Override
    public ResponseEntity<String> deleteAuthorById(Long authorId) {
        if(!authorRepository.existsById(authorId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found for delete.");
        }

        authorRepository.deleteById(authorId);
        return ResponseEntity.ok("Author deleted successfully.");
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
}