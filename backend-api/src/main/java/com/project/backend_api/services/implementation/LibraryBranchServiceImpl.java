package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.mappers.LibraryBranchDTOMapper;
import com.project.backend_api.models.Author;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.LibraryBranch;
import com.project.backend_api.repositories.BookRepository;
import com.project.backend_api.repositories.LibraryBranchRepository;
import com.project.backend_api.request.CreateLibraryBranchRequest;
import com.project.backend_api.services.LibraryBranchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LibraryBranchServiceImpl implements LibraryBranchService {

    private final LibraryBranchRepository libraryBranchRepository;
    private final LibraryBranchDTOMapper libraryBranchDTOMapper;
    private final BookRepository bookRepository;

    public LibraryBranchServiceImpl(LibraryBranchRepository libraryBranchRepository, LibraryBranchDTOMapper libraryBranchDTOMapper, BookRepository bookRepository) {
        this.libraryBranchRepository = libraryBranchRepository;
        this.libraryBranchDTOMapper = libraryBranchDTOMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<LibraryBranchDTO> getLibraryBranchByName(String branchName) {
        return libraryBranchRepository.findByBranchName(branchName).map(libraryBranchDTOMapper);
    }

    @Override
    public List<LibraryBranchDTO> getAllLibraryBranches() {
        return libraryBranchRepository.findAll().stream().map(libraryBranchDTOMapper).collect(Collectors.toList());
    }

    @Override
    public void createLibraryBranch(CreateLibraryBranchRequest request) {
        Set<Book> books = bookRepository.findAllById(request.getBookIds()).stream().collect(Collectors.toSet());

        if (books.isEmpty()) {
            throw new IllegalArgumentException("At least one valid book is required");
        }

        LibraryBranch libraryBranch = new LibraryBranch();
        libraryBranch.setBranchName(request.getBranchName());
        libraryBranch.setBranchAddress(request.getBranchAddress());
        libraryBranch.setContactNumber(request.getBranchNunber());
        libraryBranch.setOpeningHours(request.getOpeningHours());
        libraryBranch.setBooks(books);
        libraryBranchRepository.save(libraryBranch);


    }

    @Override
    public ResponseEntity<String> updateLibraryBranch(Long libraryBranchId, CreateLibraryBranchRequest request) {
        LibraryBranch libraryBranch = libraryBranchRepository.findById(libraryBranchId).orElseThrow(() -> new IllegalArgumentException("Library branch with ID " + libraryBranchId + " not found"));


        if (request.getBranchName() != null) {
            libraryBranch.setBranchName(request.getBranchName());
        }

        if (request.getBranchAddress() != null) {
            libraryBranch.setBranchAddress(request.getBranchAddress());
        }

        if (request.getBranchNunber() != null) {
            libraryBranch.setContactNumber(request.getBranchNunber());
        }

        if (request.getOpeningHours() != null) {
            libraryBranch.setOpeningHours(request.getOpeningHours());
        }

        if (request.getBookIds() != null && !request.getBookIds().isEmpty()) {
            Set<Book> books = bookRepository.findAllById(request.getBookIds()).stream().collect(Collectors.toSet());

            if (books.isEmpty()) {
                throw new IllegalArgumentException("At least one valid book id is required");
            }
        }

        libraryBranchRepository.save(libraryBranch);
        return ResponseEntity.ok("Library branch updated");
    }


    @Override
    public ResponseEntity<String> deleteLibraryBranch(Long libraryBranchId) {
        if (!libraryBranchRepository.existsById(libraryBranchId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LibraryBranch not found to be deleted.");
        }

        libraryBranchRepository.deleteById(libraryBranchId);
        return ResponseEntity.ok("LibraryBranch deleted successfully.");
    }

}