package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import com.endava.tmd.BookProject.repositories.RentedBookRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForRentBookService {

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    @Autowired
    private RentedBookRepository rentedBookRepository;

    public List<ForRentBook> getAllBooksAvailableForRent(){
        return forRentBookRepository.getAllBooksAvailableForRent();
    }

    public ResponseEntity<?> getForRentBooksByTitleOrAuthor(Optional<String> title, Optional<String> author){
        List<ForRentBook> bookList = forRentBookRepository.getForRentBooksByTitleOrAuthor(title,author);
        List<JSONObject> responseList = new ArrayList<>();
        for (ForRentBook forRentBook : bookList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("BookTitle",forRentBook.getUsersBooks().getBook().getTitle());
            jsonObject.put("BookAuthor",forRentBook.getUsersBooks().getBook().getAuthor());
            jsonObject.put("BookOwner",forRentBook.getUsersBooks().getUser().getFirstname()+" "+forRentBook.getUsersBooks().getUser().getLastname());
            jsonObject.put("AvailabilityStatus",forRentBook.getAvailable_for_renting());
            if(!forRentBook.getAvailable_for_renting()){
                jsonObject.put("AvailableDate",rentedBookRepository.getRentedBookByForRentBook(forRentBook).getReturnDate());
            }
            responseList.add(jsonObject);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList.toString());
    }
}
