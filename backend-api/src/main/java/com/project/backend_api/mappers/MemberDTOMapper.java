package com.project.backend_api.mappers;
import com.project.backend_api.dto.*;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.BookLoan;
import com.project.backend_api.models.Member;
import org.springframework.stereotype.Service;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MemberDTOMapper implements Function<Member, MemberDTO> {

    @Override
    public MemberDTO apply(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                member.getMemberStatus(),
                member.getBookLoans()
                        .stream()
                        .map(this::mapToBookLoanDTO).collect(Collectors.toList()),
                null
        );
    }

    private BookLoanDTO mapToBookLoanDTO(BookLoan bookLoan) {
        return new BookLoanDTO(
                null,
                mapToBookDTO(bookLoan.getBook()),
                mapToMemberDTO(bookLoan.getMember()),
                bookLoan.getFine().getAmount(),
                bookLoan.getLoanDate(),
                bookLoan.getReturnDate()
        );
    }
    private BookDTO mapToBookDTO(Book book) {
        return new BookDTO(
                null,
                book.getTitle(),
                null,
                new GenreDTO(null, book.getGenre().getName()),
                null,
                null,
                null,
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
