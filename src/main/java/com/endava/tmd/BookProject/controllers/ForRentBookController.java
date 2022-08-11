package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.config.SwaggerConfig;
import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.services.ForRentBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SwaggerConfig.FOR_RENT_TAG})
@RequestMapping("api/forRentBooks")
public class ForRentBookController {

    @Autowired
    private ForRentBookService forRentBookService;

    @ApiOperation(
            value = "Find all books available for rent",
            notes = "Returns all books available for rent")
    @RequestMapping(
            value="/availableBooks",
            method = RequestMethod.GET)
    public List<ForRentBook> getAllBooksAvailableForRent(){
        return forRentBookService.getAllBooksAvailableForRent();
    }

    @ApiOperation(
            value = "Find all books given for rent",
            notes = "Returns all books given for rent")
    @RequestMapping(method = RequestMethod.GET)
    public List<ForRentBook> getAllForRentBooks(){
        return forRentBookService.getAllForRentBooks();
    }

    @ApiOperation(
            value = "Find all books given for rent by title or author",
            notes = "Returns all books given for rent by title or author")
    @RequestMapping(
            value = "/titleOrAuthor",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getForRentBooksByTitleOrAuthor(@RequestParam(value="title") Optional<String> title, @RequestParam(value = "author") Optional<String> author){
        return forRentBookService.getForRentBooksByTitleOrAuthor(title, author);
    }

    @ApiOperation(
            value = "A user can rent a book",
            notes = "The user with the specified id rents the book given for rent with its specified id")
    @RequestMapping(
            value = "/rentBook",
            method = RequestMethod.POST)
    public ResponseEntity<?> rentBook(@RequestParam(value = "forRentBookId") Long forRentBookId, @RequestParam(value = "rentingUserId") Long rentingUserId){
        return forRentBookService.rentBook(forRentBookId, rentingUserId);
    }
}
