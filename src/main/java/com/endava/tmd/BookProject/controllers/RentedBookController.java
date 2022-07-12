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
@RequestMapping("/rentedBooks")
public class RentedBookController {

    @Autowired
    private RentedBookService rentedBookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RentedBook> getAllRentedBooks(){
        return rentedBookService.getAllRentedBooks();
    }

    @RequestMapping(
            value="/bookOwnerUserID",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentedBooksByBookUserId(@RequestParam("bookOwnerUserId") Long bookOwnerUserId){
        return rentedBookService.getRentedBooksByBookUserId(bookOwnerUserId);
    }

    @RequestMapping(
            value = "/extendBookRentPeriod",
            method = RequestMethod.PUT)
    public ResponseEntity<?> extendBookRentPeriod(@RequestParam("rentedBookId") Long rentedBookId){
        return rentedBookService.extendBookRentPeriod(rentedBookId);
    }

    @RequestMapping(
            value="/borrowerUserID",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersRentedBooks(@RequestParam("userId") Long userId){
        return rentedBookService.getUsersRentedBooks(userId);
    }
}
