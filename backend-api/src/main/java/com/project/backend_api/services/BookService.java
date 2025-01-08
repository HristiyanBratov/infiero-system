package com.project.backend_api.services;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.request.BookRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
public void createBook(BookRequest request);
    public void updateBook(Long id , BookRequest request);
    public BookDTO getBook(Long bookId);
    public BookDTO getBookByIsbn(String isbn);
    public List<BookDTO> getBooksByGenre(String genre);
    public List<BookDTO> getAllBooks();
    public ResponseEntity<String> deleteBook(Long bookId);
}