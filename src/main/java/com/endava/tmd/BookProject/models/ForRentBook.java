package com.endava.tmd.BookProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "for_rent_books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForRentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long for_rent_book_id;

    @OneToOne
    @JoinColumn(name = "users_books_id")
    private UsersBooks usersBooks;

    @Column()
    private RentPeriod rent_period;

    @Column()
    private ExtendRentPeriod extend_rent_period;

    @Column(columnDefinition = "boolean default true")
    private Boolean available_for_renting;
}

