package com.project.backend_api.dto;

import java.time.LocalDate;

public record BookDTO(
        String title,
        String isbn,
        LocalDate publicationDate,
        Integer copiesAvailable
        //to be continued..
) {
}
