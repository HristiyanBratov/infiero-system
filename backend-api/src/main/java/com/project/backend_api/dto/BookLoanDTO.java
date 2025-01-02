package com.project.backend_api.dto;

import java.time.LocalDate;
import java.util.Optional;

public record BookLoanDTO(Long id, BookDTO book, MemberDTO member, Optional<FineDTO> fine, LocalDate loanDate, LocalDate returnDate) {
}