package com.project.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.backend_api.models.Status;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MemberDTO(Long id, String firstName, String lastName, String email,
                        Status memberStatus, Set<BookLoanDTO> bookLoans,
                        Set<ReservationDTO> reservations){
}