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
    @Column(name="for_rent_book_id", nullable = false, updatable = false)
    private Long forRentBookId;

    @OneToOne
    @JoinColumn(name = "users_books_id")
    private UsersBooks usersBooks;

    @Column(name="rent_period")
    private RentPeriod rentPeriod;

    @Column(name="extend_rent_period")
    private ExtendRentPeriod extendRentPeriod;

    @Column(name="available_for_renting", columnDefinition = "boolean default true")
    private Boolean availableForRenting;

    public Long getForRentBookId() {
        return forRentBookId;
    }

    public UsersBooks getUsersBooks() {
        return usersBooks;
    }

    public void setUsersBooks(UsersBooks usersBooks) {
        this.usersBooks = usersBooks;
    }

    public RentPeriod getRentPeriod() {
        return rentPeriod;
    }

    public void setRentPeriod(RentPeriod rent_period) {
        this.rentPeriod = rent_period;
    }

    public ExtendRentPeriod getExtendRentPeriod() {
        return extendRentPeriod;
    }

    public void setExtendRentPeriod(ExtendRentPeriod extend_rent_period) {
        this.extendRentPeriod = extend_rent_period;
    }

    public Boolean getAvailableForRenting() {
        return availableForRenting;
    }

    public void setAvailableForRenting(Boolean available_for_renting) {
        this.availableForRenting = available_for_renting;
    }
}

