package com.project.backend_api.dto;

import java.util.Set;

public record AuthorDTO(Long id, String firstName, String lastName, Set<BookDTO> books) {
}