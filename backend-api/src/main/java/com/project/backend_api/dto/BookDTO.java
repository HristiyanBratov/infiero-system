package com.project.backend_api.dto;

import java.util.Set;

public record BookDTO(Long id, String title, Integer copiesAvailable,
                      GenreDTO genre, Set<AuthorDTO> authors, Set<LibraryBranchDTO> libraryBranches,
                      Set<BookLoanDTO> bookLoans, Set<ReservationDTO> reservations){
}