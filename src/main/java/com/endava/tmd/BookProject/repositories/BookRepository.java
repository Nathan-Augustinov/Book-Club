package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookByTitleOrAuthor(Optional<String> title, Optional<String> author);
}
