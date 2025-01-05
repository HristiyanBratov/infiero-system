package com.project.backend_api.services;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.models.Book;
import java.util.List;

public interface BookService {
    public String createBook(Book book);
    public String updateBook(Book book);
    public String deleteBook(Long bookId);
    public Book getBook(Long bookId);
    public List<BookDTO> getAllBooks();
}
