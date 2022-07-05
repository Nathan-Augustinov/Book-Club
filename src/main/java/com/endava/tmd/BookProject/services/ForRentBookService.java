package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForRentBookService {

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    public List<ForRentBook> getAllBooksAvailableForRent(){
        return forRentBookRepository.findAllBooksAvailableForRent();
    }



}
