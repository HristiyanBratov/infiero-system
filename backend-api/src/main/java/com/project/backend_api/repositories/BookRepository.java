package com.project.backend_api.repositories;

import com.project.backend_api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle (String title);

    @Query("SELECT bk FROM Book bk WHERE bk.isbn = :isbn")
    Optional<Book> findBookByIsbn(@Param("isbn") String isbn);

    @Query("SELECT bk FROM Book bk WHERE bk.genre.name = :genreName")
    List<Book> findBooksByGenreName(@Param("genreName") String genreName);
}