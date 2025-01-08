package com.project.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.backend_api.models.Status;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FineDTO(Long id, Double amount, LocalDate dueDate, Status status, MemberDTO member, BookLoanDTO bookLoan) {
}