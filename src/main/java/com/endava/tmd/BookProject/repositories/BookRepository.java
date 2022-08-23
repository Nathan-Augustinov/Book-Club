package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> getBooksByTitleOrAuthor(Optional<String> title, Optional<String> author);
    @Query("select b from Book b where b.author = :searchInput or b.title = :searchInput")
    List<Book> getBooksByTitleOrAuthor(String searchInput);

    @Query("select b from Book b where b.bookId in ( select ub.book.bookId from UsersBooks ub where ub.user.userId = :userId)")
    List<Book> getBooksByUserId(Long userId);
}
