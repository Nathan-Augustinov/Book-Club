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

    @Query("select rb from RentedBook rb where rb.forRentBook.usersBooks.user.userId = :userId")
    List<RentedBook> getRentedBooksByBookUserId(Long userId);

    @Transactional
    @Modifying
    @Query("update RentedBook rb set rb.returnDate = :newReturnDate where rb.rentedBookId = :rentedBookId")
    void extendReturnDate(Long rentedBookId, LocalDate newReturnDate);

    @Query("select rb from RentedBook rb where rb.rentUser.userId = :userId")
    List<RentedBook> getUsersRentedBooks(Long userId);

    RentedBook getRentedBookByForRentBook(ForRentBook forRentBook);

}
