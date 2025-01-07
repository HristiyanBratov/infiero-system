package com.project.backend_api.services;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.Genre;
import com.project.backend_api.request.CreateBookRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
public void createBook(CreateBookRequest request);
    public void updateBook(Long id ,CreateBookRequest request);
    public ResponseEntity<String> deleteBook(Long bookId);
    public BookDTO getBook(Long bookId);
    public List<BookDTO> getAllBooks();
}
