package com.endava.tmd.BookProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rented_books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long rented_book_id;

    @ManyToOne
    @JoinColumn(name = "for_rent_book_id")
    private ForRentBook forRentBook;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User rent_user;

    @Column(nullable = false)
    private LocalDate returnDate;

    public ForRentBook getForRentBook() {
        return forRentBook;
    }

    public void setForRentBook(ForRentBook forRentBook) {
        this.forRentBook = forRentBook;
    }

    public Long getRented_book_id() {
        return rented_book_id;
    }

    public User getRent_user() {
        return rent_user;
    }

    public void setRent_user(User rent_user) {
        this.rent_user = rent_user;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
