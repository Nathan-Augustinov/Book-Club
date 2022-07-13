package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.config.SwaggerConfig;
import com.endava.tmd.BookProject.models.RentedBook;
import com.endava.tmd.BookProject.services.RentedBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = {SwaggerConfig.RENT_TAG})
@RequestMapping("/rentedBooks")
public class RentedBookController {

    @Autowired
    private RentedBookService rentedBookService;

    @ApiOperation(
            value = "Find all rented books",
            notes = "Returns all rented books")
    @RequestMapping(method = RequestMethod.GET)
    public List<RentedBook> getAllRentedBooks(){
        return rentedBookService.getAllRentedBooks();
    }

    @ApiOperation(
            value = "Find all rented books by book owner",
            notes = "Returns all rented books owned by the user with the specified id")
    @RequestMapping(
            value="/bookOwnerUserID",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRentedBooksByBookUserId(@RequestParam("bookOwnerUserId") Long bookOwnerUserId){
        return rentedBookService.getRentedBooksByBookUserId(bookOwnerUserId);
    }

    @ApiOperation(
            value = "A user can extend its rented book rent period",
            notes = "Extends the specified rented book's rent period ")
    @RequestMapping(
            value = "/extendBookRentPeriod",
            method = RequestMethod.PUT)
    public ResponseEntity<?> extendBookRentPeriod(@RequestParam("rentedBookId") Long rentedBookId){
        return rentedBookService.extendBookRentPeriod(rentedBookId);
    }

    @ApiOperation(
            value = "Find all rented books by borrower",
            notes = "Returns all books rented by the user with the specified id")
    @RequestMapping(
            value="/borrowerUserID",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersRentedBooks(@RequestParam("userId") Long userId){
        return rentedBookService.getUsersRentedBooks(userId);
    }
}
