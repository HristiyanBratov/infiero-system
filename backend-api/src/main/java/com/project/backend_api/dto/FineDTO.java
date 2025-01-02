package com.project.backend_api.dto;

import com.project.backend_api.models.Status;

import java.time.LocalDate;

public record FineDTO(Long id, Double amount, LocalDate dueDate, Status status, MemberDTO member, BookLoanDTO bookLoan) {
}