package com.project.backend_api.dto;

import java.util.Set;

public record LibraryBranchDTO(Long id, String branchName, String branchAddress,
                               String contactNumber, String openingHours, Set<BookDTO> books) {
}