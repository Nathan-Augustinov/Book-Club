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
    @Column(name="rented_book_id",
            nullable = false,
            updatable = false)
    private Long rentedBookId;

    @ManyToOne
    @JoinColumn(name = "for_rent_book_id")
    private ForRentBook forRentBook;

    @ManyToOne
    @JoinColumn(name = "renter_user_id")
    private User rentUser;

    @Column(nullable = false)
    private LocalDate returnDate;

    public ForRentBook getForRentBook() {
        return forRentBook;
    }

    public void setForRentBook(ForRentBook forRentBook) {
        this.forRentBook = forRentBook;
    }

    public Long getRentedBookId() {
        return rentedBookId;
    }

    public User getRentUser() {
        return rentUser;
    }

    public void setRentUser(User rent_user) {
        this.rentUser = rent_user;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
