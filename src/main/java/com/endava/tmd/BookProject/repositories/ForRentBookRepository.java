package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.Book;
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

    @Query("select fr from ForRentBook fr where fr.available_for_renting = true")
    List<ForRentBook> getAllBooksAvailableForRent();

    @Transactional
    @Modifying
    @Query("update ForRentBook fr set fr.rent_period = :rentPeriod where fr.for_rent_book_id = :for_rent_book_id")
    void updateRentPeriod(RentPeriod rentPeriod, Long for_rent_book_id);

    @Query("select fr.rent_period from ForRentBook fr where fr.for_rent_book_id = :for_rent_book_id")
    RentPeriod getRentPeriodFromId(Long for_rent_book_id);

    @Transactional
    @Modifying
    @Query("update ForRentBook fr set fr.available_for_renting = false where fr.for_rent_book_id = :for_rent_book_id")
    void updateAvailabilityStatus(Long for_rent_book_id);

    //void updateExtendedRentPeriod(Long for_rent_book_id, ExtendRentPeriod extendRentPeriod);


}
