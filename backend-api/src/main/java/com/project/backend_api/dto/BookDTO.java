package com.project.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookDTO(Long id, String title, Integer copiesAvailable,
                      GenreDTO genre, List<AuthorDTO> authors, List<LibraryBranchDTO> libraryBranches,
                      List<BookLoanDTO> bookLoans, List<ReservationDTO> reservations) {
}