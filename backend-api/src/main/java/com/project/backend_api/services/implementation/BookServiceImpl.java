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
    public String createBook(Book book) {
        bookRepository.save(book);
        return "Book created successfully.";
    }

    @Override
    public String updateBook(Book book) {
        bookRepository.save(book);
        return "Book created successfully.";
    }

    @Override
    public String deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
        return "Deleted successfully.";
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
    }



}