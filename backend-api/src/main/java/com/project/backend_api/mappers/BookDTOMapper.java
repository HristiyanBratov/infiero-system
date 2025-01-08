package com.project.backend_api.mappers;

import com.project.backend_api.dto.*;
import com.project.backend_api.models.*;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookDTOMapper implements Function<Book, BookDTO> {

    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getCopiesAvailable(),
                new GenreDTO(null, book.getGenre().getName()),
                book.getAuthors()
                        .stream()
                        .map(this::mapToAuthorDTO)
                        .collect(Collectors.toList()),
                book.getLibraryBranches()
                        .stream()
                        .map(this::mapToLibraryBranchDTO)
                        .collect(Collectors.toList()),
                book.getBookLoans()
                        .stream()
                        .map(this::mapToBookLoanDTO)
                       .collect(Collectors.toList()),
                book.getReservations()
                        .stream()
                        .map(this::mapToReservationDTO).
                        collect(Collectors.toList())
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

    private BookLoanDTO mapToBookLoanDTO(BookLoan bookLoan) {
        return new BookLoanDTO(
                null,
                null,
                mapToMemberDTO(bookLoan.getMember()),
                bookLoan.getFine().getAmount(),
                bookLoan.getLoanDate(),
                bookLoan.getReturnDate()
        );
    }

    private ReservationDTO mapToReservationDTO(Reservation reservation) {
        return new ReservationDTO(
                null,
                mapToMemberDTO(reservation.getMember()),
                null
        );
    }

    private MemberDTO mapToMemberDTO(Member member) {
        return new MemberDTO(
                null,
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                null,
                null,
                null
        );
    }
}