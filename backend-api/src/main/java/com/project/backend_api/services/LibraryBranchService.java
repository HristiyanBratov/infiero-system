package com.project.backend_api.services;

import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.request.LibraryBranchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LibraryBranchService {
    public void createLibraryBranch(LibraryBranchRequest request);
    public void updateLibraryBranch(Long libraryBranchId, LibraryBranchRequest request);
    public Optional<LibraryBranchDTO> getLibraryBranchByName(String branchName);
    public List<LibraryBranchDTO> getAllLibraryBranches();
    public ResponseEntity<String> deleteLibraryBranch(Long libraryBranchId);
}