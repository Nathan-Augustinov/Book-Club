package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.UsersBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersBooksRepository extends JpaRepository<UsersBooks, Long> {

    @Query("select b from Book b where b.book_id in (select ub.book.book_id from UsersBooks ub where ub.user.user_id = :user_id)")
    List<Book> getUsersBooksByUserId(Long user_id);
}
