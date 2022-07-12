package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.UsersBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UsersBooksRepository extends JpaRepository<UsersBooks, Long> {

    @Query("select b from Book b where b.bookId in (select ub.book.bookId from UsersBooks ub where ub.user.userId = :userId)")
    List<Book> getUsersBooksByUserId(Long userId);
}
