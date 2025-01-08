package com.project.backend_api.services;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.request.AuthorRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    public void createAuthor(AuthorRequest request);
    public void saveAuthor(Long authorId, AuthorRequest request);
    public AuthorDTO getAuthorById(Long authorId);
    public List<AuthorDTO> getAllAuthors();
    public ResponseEntity<String> deleteAuthorById(Long authorId);
}