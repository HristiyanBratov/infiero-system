package com.project.backend_api.controllers;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.LibraryBranch;
import com.project.backend_api.request.CreateLibraryBranchRequest;
import com.project.backend_api.services.BookService;
import com.project.backend_api.services.LibraryBranchService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.Optional;

@RestController
@Tag(name = "Library branches", description = "Endpoints for operations involving library branches")
@RequestMapping("/libraryBranches")
public class LibraryBranchController {
    private final LibraryBranchService libraryBranchService;

    public LibraryBranchController(LibraryBranchService libraryBranchService) {
        this.libraryBranchService = libraryBranchService;
    }

    @GetMapping("/search")
    @Operation(summary = "Searches book by name", description = "Returns a library branch")
    public ResponseEntity<LibraryBranchDTO> getLibraryBranchByName(@RequestParam String branchName) {
        return libraryBranchService.getLibraryBranchByName(branchName)
                .map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping()
    @Operation(summary = "Get all library branches", description = "Returns a list of the library branches")
    public List<LibraryBranchDTO> getAllLibraryBranchesDetails() {
        return libraryBranchService.getAllLibraryBranches();
    }

    @PostMapping
    @Operation(summary = "Create a library branch", description = "Creates a library with the given details")
    public ResponseEntity<String> createLibraryBranch(@Valid @RequestBody CreateLibraryBranchRequest request) {
        libraryBranchService.createLibraryBranch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Library branch created successfully");
    }

    @PutMapping
    @Operation(summary = "Update a library", description = "Updates the information about a given library")
    public ResponseEntity<String> updateLibraryBranch(@RequestParam Long libraryId, @RequestBody CreateLibraryBranchRequest request) {
        libraryBranchService.updateLibraryBranch(libraryId, request);
        return ResponseEntity.ok("Library branch updated successfully.");
    }

    @DeleteMapping("{libraryBranchId}")
    @Operation(summary = "Delete a library", description = "Deletes a library by the given id")
    public ResponseEntity<String> deleteLibraryBranch(@PathVariable("libraryBranchId") Long libraryBranchId) {
        libraryBranchService.deleteLibraryBranch(libraryBranchId);
        return ResponseEntity.ok("Library branch deleted successfully.");
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