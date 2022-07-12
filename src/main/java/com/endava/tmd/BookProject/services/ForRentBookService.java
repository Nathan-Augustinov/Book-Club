package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.*;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import com.endava.tmd.BookProject.repositories.RentedBookRepository;
import com.endava.tmd.BookProject.repositories.UserRepository;
import com.endava.tmd.BookProject.repositories.WaitingListRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForRentBookService {

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    @Autowired
    private RentedBookRepository rentedBookRepository;

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ForRentBook> getAllForRentBooks(){
        return forRentBookRepository.findAll();
    }

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
            jsonObject.put("AvailabilityStatus",forRentBook.getAvailableForRenting());
            if(!forRentBook.getAvailableForRenting()){
                jsonObject.put("AvailableDate",rentedBookRepository.getRentedBookByForRentBook(forRentBook).getReturnDate());
            }
            responseList.add(jsonObject);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseList.toString());
    }

    public static Period transformRentPeriodInTime(RentPeriod rentPeriod){
        if(rentPeriod == RentPeriod.ONE_WEEK)
            return Period.of(0,0,7);
        else if(rentPeriod == RentPeriod.TWO_WEEKS)
            return Period.of(0,0,14);
        else if(rentPeriod == RentPeriod.THREE_WEEKS)
            return Period.of(0,0,21);
        else
            return Period.of(0,1,0);
    }

    public void addIntoTheWaitingList(ForRentBook forRentBook, User rentingUser){
        WaitingList newWaitingListEntry = new WaitingList(null,rentingUser,forRentBook.getUsersBooks());
        waitingListRepository.saveAndFlush(newWaitingListEntry);
    }

    public void addIntoTheRentedBooks(ForRentBook forRentBook, User rentingUser){
        RentPeriod newRentPeriod = RentPeriod.ONE_MONTH;
        forRentBookRepository.updateRentPeriod(newRentPeriod,forRentBook.getForRentBookId());
        RentedBook newRentedBook = new RentedBook(null,forRentBook,rentingUser, LocalDate.now().plus(ForRentBookService.transformRentPeriodInTime(newRentPeriod)));
        rentedBookRepository.saveAndFlush(newRentedBook);
        forRentBookRepository.updateAvailabilityStatus(forRentBook.getForRentBookId());
    }

    public ResponseEntity<?> rentBook(Long forRentBookId, Long rentingUserId){
        ForRentBook forRentBook = forRentBookRepository.findById(forRentBookId).orElse(null);
        User rentingUser = userRepository.findById(rentingUserId).orElse(null);
        if(forRentBook == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("For rent book id given not a correct one!");
        }
        if(rentingUser == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User id given not a correct one!");
        }
        Long bookOwnerUserId = forRentBook.getUsersBooks().getUser().getUserId();
        if(rentingUserId.equals(bookOwnerUserId)){
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("User cannot borrow his own book!");
            }
        Boolean rentingAvailable = forRentBook.getAvailableForRenting();
        if(!rentingAvailable){
            Long existingRentUserId = rentedBookRepository.getRentedBookByForRentBook(forRentBook).getRentUser().getUserId();
            if(existingRentUserId.equals(rentingUserId)){
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("User already has the book borrowed!");
            }
            addIntoTheWaitingList(forRentBook, rentingUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User is on the waiting list for borrowing the book.");
        }
        else{
            addIntoTheRentedBooks(forRentBook, rentingUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User successfully rented the book.");
        }
    }
}
