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
}
