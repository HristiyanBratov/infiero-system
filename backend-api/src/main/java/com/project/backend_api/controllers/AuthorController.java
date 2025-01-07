package com.project.backend_api.controllers;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.models.Author;
import com.project.backend_api.models.Book;
import com.project.backend_api.request.CreateAuthorRequest;
import com.project.backend_api.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Authors", description = "Endpoints for operations involving authors")
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //Read Specific Author Details from DB
    @GetMapping("{authorId}")
    @Operation(summary = "Searches an author by id", description = "Returns an author.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "author is in the system"),
            @ApiResponse(responseCode = "500", description = "author does not exist")})
    public AuthorDTO getAuthorDetails(@PathVariable("authorId") Long authorId){
        return authorService.getAuthorById(authorId);
    }

    //Read All Authors Details from DB
    @GetMapping()
    @Operation(summary = "Get all authors", description = "Returns a list of the authors.")
    public List<AuthorDTO> getAllAuthorsDetails(){
        return authorService.getAllAuthors();
    }

    @PostMapping
    @Operation(summary = "Create a author", description = "Creates an author with the given details")
    public ResponseEntity<String> createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest){
        authorService.createAuthor(createAuthorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author created successfully.");
    }

    @PutMapping
    @Operation(summary = "Update an author", description = "Updates the information about a given author")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
        return ResponseEntity.ok("Author updated successfully.");
    }

    @DeleteMapping("{authorId}")
    @Operation(summary = "Delete an author", description = "Deletes an author by the given id")
    public ResponseEntity<String> deleteAuthor(@PathVariable("authorId") Long authorId){
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.ok("Author deleted successfully.");
    }

}