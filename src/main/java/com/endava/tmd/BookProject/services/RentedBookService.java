package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.*;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import com.endava.tmd.BookProject.repositories.RentedBookRepository;
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
    private ForRentBookRepository forRentBookRepository;

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private ForRentBookService forRentBookService;

    @Scheduled(cron = "0 0 0 * * *")
    public void rentBookToWaitingListUserWhenReturnDateIsDue(){
        List<RentedBook> rentedBooks = rentedBookRepository.findAll();
        for (RentedBook rentedBook : rentedBooks) {
            if(rentedBook.getReturnDate().equals(LocalDate.now().minusDays(1))){
                Long forRentBookId = rentedBook.getForRentBook().getForRentBookId();
                UsersBooks usersBooks = rentedBook.getForRentBook().getUsersBooks();
                forRentBookRepository.resetRentPeriodExtendRentPeriodAndAvailabilityStatus(forRentBookId);
                rentedBookRepository.delete(rentedBook);
                Long waitingListId = waitingListRepository.getFirstByUsersBooks(usersBooks).getWaitingListId();
                Long nextUserId = waitingListRepository.getFirstByUsersBooks(usersBooks).getUser().getUserId();
                forRentBookService.rentBook(forRentBookId,nextUserId, RentPeriod.ONE_WEEK);
                waitingListRepository.deleteById(waitingListId);
            }
        }
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

    public ResponseEntity<?> getRentedBooksByBookUserId(Long userId) {
        List<RentedBook> rentedBooksByBookUserIdList = rentedBookRepository.getRentedBooksByBookUserId(userId);
        List<JSONObject> responseList = new ArrayList<>();
        for (RentedBook rentedBook : rentedBooksByBookUserIdList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("BookTitle",rentedBook.getForRentBook().getUsersBooks().getBook().getTitle());
            jsonObject.put("BookAuthor",rentedBook.getForRentBook().getUsersBooks().getBook().getAuthor());
            jsonObject.put("ReturnDate",rentedBook.getReturnDate());
            jsonObject.put("BorrowerName",rentedBook.getRentUser().getFirstname()+" "+rentedBook.getRentUser().getLastname());
            responseList.add(jsonObject);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseList.toString());
    }

    public ResponseEntity<?> extendBookRentPeriod(Long rentedBookId){
        ExtendRentPeriod extendRentPeriod = ExtendRentPeriod.ONE_WEEK;
        RentedBook rentedBook = rentedBookRepository.findById(rentedBookId).orElse(null);
        if(rentedBook == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Rented book id given is not a correct one!");
        }
        Long forRentBookId = rentedBook.getForRentBook().getForRentBookId();
        forRentBookRepository.updateExtendedRentPeriod(forRentBookId, extendRentPeriod);
        LocalDate newReturnDate = rentedBook.getReturnDate().plus(RentedBookService.transformExtendRentPeriodInTime(extendRentPeriod));
        rentedBookRepository.extendReturnDate(rentedBookId, newReturnDate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Rented book's return date extended with" + " " + extendRentPeriod.toString().replace("_"," ").toLowerCase() + "!");
    }

    public ResponseEntity<?> getUsersRentedBooks(Long userId){
        List<RentedBook> usersRentedBooks = rentedBookRepository.getUsersRentedBooks(userId);
        List<JSONObject> responseList = new ArrayList<>();
        for (RentedBook usersRentedBook : usersRentedBooks) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("BookTitle", usersRentedBook.getForRentBook().getUsersBooks().getBook().getTitle());
            jsonObject.put("BookAuthor",usersRentedBook.getForRentBook().getUsersBooks().getBook().getAuthor());
            jsonObject.put("ReturnDate",usersRentedBook.getReturnDate());
            responseList.add(jsonObject);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseList.toString());
    }

}
