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
public class LibraryBranchDTOMapper implements Function<LibraryBranch, LibraryBranchDTO> {
    @Override
    public LibraryBranchDTO apply(LibraryBranch libraryBranch) {
        return new LibraryBranchDTO(
                libraryBranch.getId(),
                libraryBranch.getBranchName(),
                libraryBranch.getBranchAddress(),
                libraryBranch.getContactNumber(),
                libraryBranch.getOpeningHours(),
                libraryBranch.getBooks()
                        .stream()
                        .map(this::mapToBookDTO)
                        .collect(Collectors.toList())
        );
    }

    private BookDTO mapToBookDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getCopiesAvailable(),
                new GenreDTO(null, book.getGenre().getName()),
                book.getAuthors()
                                .stream()
                                .map(this::mapToAuthorDTO)
                                .collect(Collectors.toList()),
                null,
                null,
                null
        );
    }

    private AuthorDTO mapToAuthorDTO(Author author) {
        return new AuthorDTO(
                null,
                author.getFirstName(),
                author.getLastName(),
                null
        );
    }
}