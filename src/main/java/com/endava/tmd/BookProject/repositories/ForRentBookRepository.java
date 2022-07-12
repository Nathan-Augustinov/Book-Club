package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.ExtendRentPeriod;
import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.models.RentPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ForRentBookRepository extends JpaRepository<ForRentBook, Long> {

    @Query("select fr from ForRentBook fr where fr.availableForRenting = true")
    List<ForRentBook> getAllBooksAvailableForRent();

    @Transactional
    @Modifying
    @Query("update ForRentBook fr set fr.rentPeriod = :rentPeriod where fr.forRentBookId = :forRentBookId")
    void updateRentPeriod(RentPeriod rentPeriod, Long forRentBookId);

    @Transactional
    @Modifying
    @Query("update ForRentBook fr set fr.availableForRenting = false where fr.forRentBookId = :forRentBookId")
    void updateAvailabilityStatus(Long forRentBookId);

    @Transactional
    @Modifying
    @Query("update ForRentBook fr set fr.extendRentPeriod = :extendRentPeriod where fr.forRentBookId = :forRentBookId")
    void updateExtendedRentPeriod(Long forRentBookId, ExtendRentPeriod extendRentPeriod);

    @Query("select fr from ForRentBook fr where fr.usersBooks.book.title = :title or fr.usersBooks.book.author= :author")
    List<ForRentBook> getForRentBooksByTitleOrAuthor(Optional<String> title, Optional<String> author);

    @Transactional
    @Modifying
    @Query("update ForRentBook fr set fr.rentPeriod = null, fr.extendRentPeriod = null, fr.availableForRenting = true where fr.forRentBookId = :forRentBookId")
    void resetRentPeriodExtendRentPeriodAndAvailabilityStatus(Long forRentBookId);


}
