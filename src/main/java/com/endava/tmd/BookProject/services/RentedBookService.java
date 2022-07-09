package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.*;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import com.endava.tmd.BookProject.repositories.RentedBookRepository;
import com.endava.tmd.BookProject.repositories.UserRepository;
import com.endava.tmd.BookProject.repositories.WaitingListRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
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

    public void addIntoTheWaitingList(Long for_rent_book_id, Long renting_user_id){
        WaitingList newWaitingListEntry = new WaitingList(null,userRepository.findById(renting_user_id).get(),forRentBookRepository.findById(for_rent_book_id).get().getUsersBooks());
        waitingListRepository.saveAndFlush(newWaitingListEntry);
    }

    public void addIntoTheRentedBooks(Long for_rent_book_id, Long renting_user_id){
        RentPeriod newRentPeriod = RentPeriod.ONE_MONTH;
        forRentBookRepository.updateRentPeriod(newRentPeriod,for_rent_book_id);
        RentedBook newRentedBook = new RentedBook(null,forRentBookRepository.findById(for_rent_book_id).get(),userRepository.findById(renting_user_id).get(), LocalDate.now().plus(RentedBookService.transformRentPeriodInTime(newRentPeriod)));
        rentedBookRepository.saveAndFlush(newRentedBook);
        forRentBookRepository.updateAvailabilityStatus(for_rent_book_id);
    }
    @Scheduled(cron = "0 0 0 * * *")
    public void rentBookToWaitingListUserWhenReturnDateIsDue(){
        List<RentedBook> rentedBooks = rentedBookRepository.findAll();
        for (RentedBook rentedBook : rentedBooks) {
            if(rentedBook.getReturnDate().equals(LocalDate.now().minusDays(1))){
                Long forRentBookId = rentedBook.getForRentBook().getFor_rent_book_id();
                UsersBooks usersBooks = rentedBook.getForRentBook().getUsersBooks();
                forRentBookRepository.resetRentPeriodExtendRentPeriodAndAvailabilityStatus(forRentBookId);
                rentedBookRepository.delete(rentedBook);
                Long waitingListId = waitingListRepository.getFirstByUsersBooks(usersBooks).getWaiting_list_id();
                Long nextUserId = waitingListRepository.getFirstByUsersBooks(usersBooks).getUser().getUser_id();
                rentBook(forRentBookId,nextUserId);
                waitingListRepository.deleteById(waitingListId);
            }
        }
    }

    public ResponseEntity<?> rentBook(Long for_rent_book_id, Long renting_user_id){
        ForRentBook forRentBook = forRentBookRepository.findById(for_rent_book_id).orElse(null);
        if(forRentBook == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("For rent book id not existing!");
        }
        Boolean rentingAvailable = forRentBook.getAvailable_for_renting();
        if(!rentingAvailable){
            Long existingRentUserId = rentedBookRepository.getRentedBookByForRentBook(forRentBook).getRent_user().getUser_id();
            Long bookOwnerUserId = rentedBookRepository.getRentedBookByForRentBook(forRentBook).getForRentBook().getUsersBooks().getUser().getUser_id();
            if(existingRentUserId.equals(renting_user_id)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already has the book borrowed!");
            }
            else if(renting_user_id.equals(bookOwnerUserId)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User cannot borrow his own book!");
            }
            addIntoTheWaitingList(for_rent_book_id, renting_user_id);
            return ResponseEntity.status(HttpStatus.OK).body("User is on the waiting list for borrowing the book.");
        }
        else{
            addIntoTheRentedBooks(for_rent_book_id, renting_user_id);
            return ResponseEntity.status(HttpStatus.OK).body("User successfully rented the book.");
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
            jsonObject.put("BorrowerName",rentedBook.getRent_user().getFirstname()+" "+rentedBook.getRent_user().getLastname());
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
