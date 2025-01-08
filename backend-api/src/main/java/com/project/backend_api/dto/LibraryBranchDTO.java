package com.project.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LibraryBranchDTO(Long id, String branchName, String branchAddress,
                               String contactNumber, String openingHours, List<BookDTO> books) {
}