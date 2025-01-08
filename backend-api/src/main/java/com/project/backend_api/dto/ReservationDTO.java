package com.project.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.backend_api.models.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReservationDTO(Long id, MemberDTO member, BookDTO book){
}