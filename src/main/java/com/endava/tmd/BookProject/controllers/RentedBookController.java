package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.services.RentedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rented_books")
public class RentedBookController {

    @Autowired
    private RentedBookService rentedBookService;

    @RequestMapping(method = RequestMethod.POST)
    public void rentBook(@RequestParam(value = "for_rent_book_id") Long for_rent_book_id, @RequestParam(value = "renting_user_id") Long renting_user_id){
        rentedBookService.rentBook(for_rent_book_id, renting_user_id);
    }
}
