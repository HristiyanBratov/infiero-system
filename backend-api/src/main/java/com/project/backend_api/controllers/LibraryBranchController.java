package com.project.backend_api.controllers;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.LibraryBranch;
import com.project.backend_api.services.BookService;
import com.project.backend_api.services.LibraryBranchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libraryBranches")
public class LibraryBranchController {
    private final LibraryBranchService libraryBranchService;

    public LibraryBranchController(LibraryBranchService libraryBranchService) {
        this.libraryBranchService = libraryBranchService;
    }

    @GetMapping("/search")
    public ResponseEntity<LibraryBranchDTO> getLibraryBranchByName(@RequestParam String branchName) {
       return libraryBranchService.getLibraryBranchByName(branchName)
               .map(ResponseEntity::ok).
               orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Read All Library Branch Details from DB
    @GetMapping()
    @Operation(summary = "Get all library branches", description = "Returns a list of the library branches.")
    public List<LibraryBranchDTO> getAllLibraryBranchesDetails() {
        return libraryBranchService.getAllLibraryBranches();
    }

    @PostMapping
    public ResponseEntity<String> createLibraryBranch(@RequestBody LibraryBranch libraryBranch){
        libraryBranchService.createLibraryBranch(libraryBranch);
        return ResponseEntity.status(HttpStatus.CREATED).body("Library branch created successfully.");
    }

    @PutMapping
    public ResponseEntity<String> updateLibraryBranch(@RequestBody LibraryBranch libraryBranch){
        libraryBranchService.updateLibraryBranch(libraryBranch);
        return ResponseEntity.ok("Library branch updated successfully.");
    }

    @DeleteMapping("{libraryBranchId}")
    public ResponseEntity<String> deleteLibraryBranch(@PathVariable("libraryBranchId") Long libraryBranchId){
        libraryBranchService.deleteLibraryBranch(libraryBranchId);
        return ResponseEntity.ok("Library branch deleted successfully.");
    }

}