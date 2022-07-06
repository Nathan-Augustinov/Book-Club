package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.RentPeriod;
import com.endava.tmd.BookProject.models.RentedBook;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import com.endava.tmd.BookProject.repositories.RentedBookRepository;
import com.endava.tmd.BookProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class RentedBookService {

    @Autowired
    private RentedBookRepository rentedBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    /*public void rentBook(Long user_id, Long book_id){
        RentPeriod currentRentPeriod = forRentBookRepository.getRentPeriodFromUsersBookId(user_id, book_id);
        System.out.println(currentRentPeriod);
        RentPeriod newRentPeriod;
        if(currentRentPeriod == null){
            newRentPeriod = RentPeriod.ONE_WEEK;
            forRentBookRepository.updateRentPeriod(newRentPeriod,user_id,book_id);
        }
        else{
            newRentPeriod=currentRentPeriod;
        }
        System.out.println(newRentPeriod);
        RentedBook newRentedBook = new RentedBook(null,forRentBookRepository.findById(book_id).get(),userRepository.findById(user_id).get(), LocalDate.now().plus(RentedBookService.transformRentPeriodInTime(newRentPeriod)));
        rentedBookRepository.saveAndFlush(newRentedBook);
    }*/
    public void rentBook(Long for_rent_book_id, Long renting_user_id){
        /*RentPeriod currentRentPeriod = forRentBookRepository.getRentPeriodFromId(for_rent_book_id);
        RentPeriod newRentPeriod;
        if(currentRentPeriod == null){
            newRentPeriod = RentPeriod.ONE_WEEK;
            forRentBookRepository.updateRentPeriod(newRentPeriod,for_rent_book_id);
        }
        else{
            newRentPeriod=currentRentPeriod;
        }*/
        RentPeriod newRentPeriod = RentPeriod.ONE_WEEK;
        forRentBookRepository.updateRentPeriod(newRentPeriod,for_rent_book_id);
        RentedBook newRentedBook = new RentedBook(null,forRentBookRepository.findById(for_rent_book_id).get(),userRepository.findById(renting_user_id).get(), LocalDate.now().plus(RentedBookService.transformRentPeriodInTime(newRentPeriod)));
        rentedBookRepository.saveAndFlush(newRentedBook);
        forRentBookRepository.updateAvailabilityStatus(for_rent_book_id);
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
}
