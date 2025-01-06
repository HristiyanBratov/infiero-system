package com.project.backend_api.mappers;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.dto.GenreDTO;
import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.models.Author;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.LibraryBranch;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AuthorDTOMapper implements Function<Author, AuthorDTO> {
    @Override
    public AuthorDTO apply(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBooks()
                        .stream()
                        .map(this::mapToBookDTO)
                        .collect(Collectors.toList())
        );
    }

    private BookDTO mapToBookDTO(Book book) {
        return new BookDTO(
                null,
                book.getTitle(),
                null,
                new GenreDTO(null, book.getGenre().getName()),
                null,
                book.getLibraryBranches()
                        .stream()
                        .map(this::mapToLibraryBranchDTO)
                        .collect(Collectors.toList()),
                null,
                null
        );
    }

    private LibraryBranchDTO mapToLibraryBranchDTO(LibraryBranch libraryBranch) {
        return new LibraryBranchDTO(
                null,
                libraryBranch.getBranchName(),
                libraryBranch.getBranchAddress(),
                libraryBranch.getContactNumber(),
                libraryBranch.getOpeningHours(),
                null
        );
    }
}