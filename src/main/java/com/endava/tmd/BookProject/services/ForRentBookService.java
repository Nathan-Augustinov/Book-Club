package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.models.RentPeriod;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForRentBookService {

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    public List<ForRentBook> getAllBooksAvailableForRent(){
        return forRentBookRepository.getAllBooksAvailableForRent();
    }

}
