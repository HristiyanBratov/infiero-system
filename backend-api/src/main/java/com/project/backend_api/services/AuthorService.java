package com.project.backend_api.services;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.models.Author;
import com.project.backend_api.request.CreateAuthorRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    public void createAuthor(CreateAuthorRequest request);
    public ResponseEntity<String> saveAuthor(Author author);
    public ResponseEntity<String> deleteAuthorById(Long authorId);
    public AuthorDTO getAuthorById(Long authorId);
    public List<AuthorDTO> getAllAuthors();
}