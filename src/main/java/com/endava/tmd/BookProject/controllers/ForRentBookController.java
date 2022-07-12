package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.services.ForRentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("forRentBooks")
public class ForRentBookController {

    @Autowired
    private ForRentBookService forRentBookService;

    @RequestMapping(value="/availableBooks",method = RequestMethod.GET)
    public List<ForRentBook> getAllBooksAvailableForRent(){
        return forRentBookService.getAllBooksAvailableForRent();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ForRentBook> getAllForRentBooks(){
        return forRentBookService.getAllForRentBooks();
    }

    @RequestMapping(value = "/titleOrAuthor", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getForRentBooksByTitleOrAuthor(@RequestParam(value="title") Optional<String> title, @RequestParam(value = "author") Optional<String> author){
        return forRentBookService.getForRentBooksByTitleOrAuthor(title, author);
    }

    @RequestMapping(value = "/rentBook", method = RequestMethod.POST)
    public ResponseEntity<?> rentBook(@RequestParam(value = "forRentBookId") Long forRentBookId, @RequestParam(value = "rentingUserId") Long rentingUserId){
        return forRentBookService.rentBook(forRentBookId, rentingUserId);
    }
}
