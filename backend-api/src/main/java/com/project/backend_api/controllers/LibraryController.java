package com.project.backend_api.controllers;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.models.Book;
import com.project.backend_api.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    private final BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    //Read Specific Book Details from DB
    @GetMapping("{bookId}")
    public Book getBookDetails(@PathVariable("bookId") int bookId){
        return bookService.getBook(bookId);
    }

    //Read All Books Details from DB
    @GetMapping()
    public List<BookDTO> getAllBookDetails(){
        return bookService.getAllBooks();
    }

    @PostMapping
    public String createBookDetails(@RequestBody Book book){
        bookService.createBook(book);
        return "Book created successfully";
    }

    @PutMapping
    public String updateBookDetails(@RequestBody Book book){
        bookService.updateBook(book);
        return "Book updated successfully";
    }

    @DeleteMapping
    public String deleteBookDetails(@PathVariable("bookId") int bookId){
        bookService.deleteBook(bookId);
        return "Book deleted successfully";
    }
}
