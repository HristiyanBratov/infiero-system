package com.project.backend_api.services.implementation;

import com.project.backend_api.models.Book;
import com.project.backend_api.repositories.BookRepository;
import com.project.backend_api.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public String createBook(Book book) {
        bookRepository.save(book);
        return "Book created successfully.";
    }

    @Override
    public String updateBook(Book book) {
        bookRepository.save(book);
        return "Book created successfully.";
    }

    @Override
    public String deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
        return "Deleted successfully.";
    }

    @Override
    public Book getBook(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
