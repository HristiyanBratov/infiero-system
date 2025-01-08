package com.project.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookLoanDTO(Long id, BookDTO book, MemberDTO member, Double fine, LocalDate loanDate, LocalDate returnDate) {
}