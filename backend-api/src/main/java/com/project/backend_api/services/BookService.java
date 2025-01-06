package com.project.backend_api.services;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.models.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    public ResponseEntity<String> createBook(Book book);
    public ResponseEntity<String> updateBook(Book book);
    public ResponseEntity<String> deleteBook(Long bookId);
    public BookDTO getBook(Long bookId);
    public List<BookDTO> getAllBooks();
}
