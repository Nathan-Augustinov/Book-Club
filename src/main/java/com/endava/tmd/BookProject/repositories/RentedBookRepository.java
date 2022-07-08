package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.RentedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentedBookRepository extends JpaRepository<RentedBook, Long> {

    @Query("select rb from RentedBook rb where rb.forRentBook.usersBooks.user.user_id = :user_id")
    List<RentedBook> getRentedBooksByBookUserId(Long user_id);

}
