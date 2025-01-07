package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.mappers.LibraryBranchDTOMapper;
import com.project.backend_api.models.LibraryBranch;
import com.project.backend_api.repositories.LibraryBranchRepository;
import com.project.backend_api.services.LibraryBranchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryBranchServiceImpl implements LibraryBranchService {

    private final LibraryBranchRepository libraryBranchRepository;
    private final LibraryBranchDTOMapper libraryBranchDTOMapper;

    public LibraryBranchServiceImpl(LibraryBranchRepository libraryBranchRepository, LibraryBranchDTOMapper libraryBranchDTOMapper) {
        this.libraryBranchRepository = libraryBranchRepository;
        this.libraryBranchDTOMapper = libraryBranchDTOMapper;
    }

    @Override
    public Optional<LibraryBranchDTO> getLibraryBranchByName(String branchName) {
        return libraryBranchRepository.findByBranchName(branchName).map(libraryBranchDTOMapper);
    }

    @Override
    public List<LibraryBranchDTO> getAllLibraryBranches() {
        return libraryBranchRepository.findAll()
                .stream()
                .map(libraryBranchDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createLibraryBranch(LibraryBranch libraryBranch) {
        libraryBranchRepository.save(libraryBranch);
        return ResponseEntity.status(HttpStatus.CREATED).body("Library branch created successfully.");
    }

    @Override
    public ResponseEntity<String> updateLibraryBranch(LibraryBranch libraryBranch) {
        if(!libraryBranchRepository.existsById(libraryBranch.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Library Branch not found for update.");
        }

        libraryBranchRepository.save(libraryBranch);
        return ResponseEntity.ok("Library Branch updated successfully.");
    }

    @Override
    public ResponseEntity<String> deleteLibraryBranch(Long libraryBranchId) {
        if(!libraryBranchRepository.existsById(libraryBranchId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LibraryBranch not found to be deleted.");
        }

        libraryBranchRepository.deleteById(libraryBranchId);
        return ResponseEntity.ok("LibraryBranch deleted successfully.");
    }

}