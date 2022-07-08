package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.ExtendRentPeriod;
import com.endava.tmd.BookProject.models.RentPeriod;
import com.endava.tmd.BookProject.models.RentedBook;
import com.endava.tmd.BookProject.models.WaitingList;
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

@Service
public class RentedBookService {

    @Autowired
    private RentedBookRepository rentedBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    @Autowired
    private WaitingListRepository waitingListRepository;

    public void rentBook(Long for_rent_book_id, Long renting_user_id){
        Boolean rentingAvailable = forRentBookRepository.findById(for_rent_book_id).get().getAvailable_for_renting();
        if(!rentingAvailable){
            WaitingList newWaitingListEntry = new WaitingList(null,userRepository.findById(renting_user_id).get(),forRentBookRepository.findById(for_rent_book_id).get().getUsersBooks());
            waitingListRepository.saveAndFlush(newWaitingListEntry);
        }
        else{
            RentPeriod newRentPeriod = RentPeriod.ONE_MONTH;
            forRentBookRepository.updateRentPeriod(newRentPeriod,for_rent_book_id);
            RentedBook newRentedBook = new RentedBook(null,forRentBookRepository.findById(for_rent_book_id).get(),userRepository.findById(renting_user_id).get(), LocalDate.now().plus(RentedBookService.transformRentPeriodInTime(newRentPeriod)));
            rentedBookRepository.saveAndFlush(newRentedBook);
            forRentBookRepository.updateAvailabilityStatus(for_rent_book_id);
        }
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

    public static Period transformExtendRentPeriodInTime(ExtendRentPeriod extendRentPeriod){
        if(extendRentPeriod == ExtendRentPeriod.ONE_WEEK)
            return Period.of(0,0,7);
        else
            return Period.of(0,0,14);

    }

    public List<RentedBook> getAllRentedBooks(){
        return rentedBookRepository.findAll();
    }

    public ResponseEntity<?> getRentedBooksByBookUserId(Long user_id) {
        List<RentedBook> rentedBooksByBookUserIdList = rentedBookRepository.getRentedBooksByBookUserId(user_id);
        List<JSONObject> responseList = new ArrayList<>();
        for (RentedBook rentedBook : rentedBooksByBookUserIdList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("BookTitle",rentedBook.getForRentBook().getUsersBooks().getBook().getTitle());
            jsonObject.put("BookAuthor",rentedBook.getForRentBook().getUsersBooks().getBook().getAuthor());
            jsonObject.put("ReturnDate",rentedBook.getReturnDate());
            jsonObject.put("BorrowerFirstname",rentedBook.getRent_user().getFirstname());
            jsonObject.put("BorrowerLastname", rentedBook.getRent_user().getLastname());
            responseList.add(jsonObject);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList.toString());
    }

    public void extendBookRentPeriod(Long rented_book_id){
        ExtendRentPeriod extendRentPeriod = ExtendRentPeriod.ONE_WEEK;
        Long for_rent_book_id = rentedBookRepository.findById(rented_book_id).get().getForRentBook().getFor_rent_book_id();
        forRentBookRepository.updateExtendedRentPeriod(for_rent_book_id, extendRentPeriod);
        LocalDate newReturnDate = rentedBookRepository.findById(rented_book_id).get().getReturnDate().plus(RentedBookService.transformExtendRentPeriodInTime(extendRentPeriod));
        rentedBookRepository.extendReturnDate(rented_book_id, newReturnDate);
    }

    public ResponseEntity<?> getUsersRentedBooks(Long user_id){
        List<RentedBook> usersRentedBooks = rentedBookRepository.getUsersRentedBooks(user_id);
        List<JSONObject> responseList = new ArrayList<>();
        for (RentedBook usersRentedBook : usersRentedBooks) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("BookTitle", usersRentedBook.getForRentBook().getUsersBooks().getBook().getTitle());
            jsonObject.put("BookAuthor",usersRentedBook.getForRentBook().getUsersBooks().getBook().getAuthor());
            jsonObject.put("ReturnDate",usersRentedBook.getReturnDate());
            responseList.add(jsonObject);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseList.toString());
    }

}
