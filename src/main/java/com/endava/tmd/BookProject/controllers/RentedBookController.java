package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.RentedBook;
import com.endava.tmd.BookProject.services.RentedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/rented_books")
public class RentedBookController {

    @Autowired
    private RentedBookService rentedBookService;

    @RequestMapping(method = RequestMethod.POST)
    public void rentBook(@RequestParam(value = "for_rent_book_id") Long for_rent_book_id, @RequestParam(value = "renting_user_id") Long renting_user_id){
        rentedBookService.rentBook(for_rent_book_id, renting_user_id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RentedBook> getAllRentedBooks(){
        return rentedBookService.getAllRentedBooks();
    }

    @RequestMapping(value="/bookOwnerUserID", params="book_owner_user_id",method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentedBooksByBookUserId(@RequestParam("book_owner_user_id") Long user_id){
        return rentedBookService.getRentedBooksByBookUserId(user_id);
    }

    @RequestMapping(value = "/extendBookRentPeriod",params = "rented_book_id", method = RequestMethod.PUT)
    public void extendBookRentPeriod(@RequestParam("rented_book_id") Long rented_book_id){
        rentedBookService.extendBookRentPeriod(rented_book_id);
    }

    @RequestMapping(value="/borrowerUserID", params="user_id",method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersRentedBooks(@RequestParam("user_id") Long user_id){
        return rentedBookService.getUsersRentedBooks(user_id);
    }
}
