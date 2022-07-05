package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.services.ForRentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("for_rent_books")
public class ForRentBookController {

    @Autowired
    private ForRentBookService forRentBookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ForRentBook> getAllBooksAvailableForRent(){
        return forRentBookService.getAllBooksAvailableForRent();
    }
}
