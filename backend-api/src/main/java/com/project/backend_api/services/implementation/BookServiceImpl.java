package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.dto.LibraryBranchDTO;
import com.project.backend_api.mappers.BookDTOMapper;
import com.project.backend_api.models.Author;
import com.project.backend_api.models.Book;
import com.project.backend_api.models.LibraryBranch;
import com.project.backend_api.models.Reservation;
import com.project.backend_api.repositories.BookRepository;
import com.project.backend_api.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;

    public BookServiceImpl(BookRepository bookRepository, BookDTOMapper bookDTOMapper) {
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
    }

    @Override
    public ResponseEntity<String> createBook(Book book) {
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully.");
    }

    @Override
    public ResponseEntity<String> updateBook(Book book) {
        if(!bookRepository.existsById(book.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found for update.");
        }

        bookRepository.save(book);
        return ResponseEntity.ok("Book updated successfully.");
    }

    @Override
    public ResponseEntity<String> deleteBook(Long bookId) {
        if(!bookRepository.existsById(bookId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found to be deleted.");
        }

        bookRepository.deleteById(bookId);
        return ResponseEntity.ok("Book deleted successfully.");
    }

    @Override
    public BookDTO getBook(Long bookId) {
        return bookRepository.findById(bookId).map(bookDTOMapper).
                orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + " not found."));
    }

    @Override
    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
    }
}