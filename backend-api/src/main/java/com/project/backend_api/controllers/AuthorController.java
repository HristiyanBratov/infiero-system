package com.project.backend_api.controllers;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.request.AuthorRequest;
import com.project.backend_api.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Authors", description = "Endpoints for operations involving authors")
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @Operation(summary = "Create a author", description = "Creates an author with the given details")
    public ResponseEntity<String> createAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
        authorService.createAuthor(authorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author created successfully.");
    }

    @PutMapping
    @Operation(summary = "Update an author", description = "Updates the information about a given author")
    public ResponseEntity<String> updateAuthor(@RequestParam Long authorId, @RequestBody AuthorRequest authorRequest) {
        authorService.saveAuthor(authorId, authorRequest);
        return ResponseEntity.ok("Author updated successfully");
    }

    @GetMapping("{authorId}")
    @Operation(summary = "Searches an author by id", description = "Returns an author.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "author is in the system"),
            @ApiResponse(responseCode = "500", description = "author does not exist")})
    public AuthorDTO getAuthorDetails(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @GetMapping()
    @Operation(summary = "Get all authors", description = "Returns a list of the authors")
    public List<AuthorDTO> getAllAuthorsDetails() {
        return authorService.getAllAuthors();
    }

    @DeleteMapping("{authorId}")
    @Operation(summary = "Delete an author", description = "Deletes an author by the given id")
    public ResponseEntity<String> deleteAuthor(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok("Author deleted successfully");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}