package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.models.RentedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface RentedBookRepository extends JpaRepository<RentedBook, Long> {

    @Query("select rb from RentedBook rb where rb.forRentBook.usersBooks.user.user_id = :user_id")
    List<RentedBook> getRentedBooksByBookUserId(Long user_id);

    @Transactional
    @Modifying
    @Query("update RentedBook rb set rb.returnDate = :newReturnDate where rb.rented_book_id = :rented_book_id")
    void extendReturnDate(Long rented_book_id, LocalDate newReturnDate);

    @Query("select rb from RentedBook rb where rb.rent_user.user_id = :user_id")
    List<RentedBook> getUsersRentedBooks(Long user_id);

    RentedBook getRentedBookByForRentBook(ForRentBook forRentBook);

}
