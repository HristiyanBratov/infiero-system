package com.project.backend_api.controllers;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.Genre;
import com.project.backend_api.request.CreateBookRequest;
import com.project.backend_api.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Books", description = "Endpoints for operations involving books")
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{bookId}")
    @Operation(summary = "Searches book by id", description = "Returns a book.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "book is in the system"), @ApiResponse(responseCode = "500", description = "book does not exist")})
    public BookDTO getBookDetails(@PathVariable("bookId") Long bookId) {
        return bookService.getBook(bookId);
    }


    @GetMapping()

    @Operation(summary = "Get all book", description = "Returns a list of the books")
    public List<BookDTO> getAllBookDetails() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @Operation(summary = "Create a book", description = "Creates a book with the given details")
    public ResponseEntity<String> createBook(@RequestBody CreateBookRequest request) {
        bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully");
    }


    @PutMapping("/{bookId}")
    @Operation(summary = "Update a book", description = "Updates the information about a given book")
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @RequestBody CreateBookRequest request) {
        bookService.updateBook(bookId, request);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("{bookId}")
    @Operation(summary = "Delete a book", description = "Deletes a book by the given id")
    public ResponseEntity<String> deleteBookDetails(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }
}