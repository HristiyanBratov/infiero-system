package com.project.backend_api.controllers;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.models.Book;
import com.project.backend_api.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Read Specific Book Details from DB
    @GetMapping("{bookId}")
    @Operation(summary = "Searches book by id", description = "Returns a book.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "book is in the system"),
            @ApiResponse(responseCode = "500", description = "book does not exist")})
    public BookDTO getBookDetails(@PathVariable("bookId") Long bookId){
        return bookService.getBook(bookId);
    }

    //Read All Books Details from DB
    @GetMapping()
    @Operation(summary = "Get all book", description = "Returns a list of the books.")
    public List<BookDTO> getAllBookDetails(){
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<String> createBookDetails(@RequestBody Book book){
        bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully.");
    }

    @PutMapping
    public ResponseEntity<String> updateBookDetails(@RequestBody Book book){
        bookService.updateBook(book);
        return ResponseEntity.ok("Book updated successfully.");
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBookDetails(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully.");
    }
}