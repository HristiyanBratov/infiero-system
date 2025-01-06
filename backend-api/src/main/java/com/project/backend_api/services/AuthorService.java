package com.project.backend_api.services;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.models.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    public ResponseEntity<String> createAuthor(Author author);
    public ResponseEntity<String> saveAuthor(Author author);
    public ResponseEntity<String> deleteAuthorById(Long authorId);
    public AuthorDTO getAuthorById(Long authorId);
    public List<AuthorDTO> getAllAuthors();
}