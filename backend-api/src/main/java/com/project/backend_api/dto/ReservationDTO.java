package com.project.backend_api.dto;

import com.project.backend_api.models.Status;

public record ReservationDTO(Long id, MemberDTO member, BookDTO book, Status status){
}