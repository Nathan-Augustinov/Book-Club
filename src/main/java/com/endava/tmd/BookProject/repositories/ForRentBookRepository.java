package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.ForRentBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForRentBookRepository extends JpaRepository<ForRentBook, Long> {

    @Query("select fr from ForRentBook fr where fr.available_for_renting = true")
    public List<ForRentBook> findAllBooksAvailableForRent();
}
