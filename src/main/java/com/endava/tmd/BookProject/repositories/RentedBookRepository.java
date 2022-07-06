package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.RentedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentedBookRepository extends JpaRepository<RentedBook, Long> {

}
