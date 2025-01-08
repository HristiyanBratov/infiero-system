package com.project.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReservationDTO(Long id, MemberDTO member, BookDTO book){
}