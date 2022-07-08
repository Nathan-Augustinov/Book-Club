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

    public Long getFor_rent_book_id() {
        return for_rent_book_id;
    }

    public UsersBooks getUsersBooks() {
        return usersBooks;
    }

    public void setUsersBooks(UsersBooks usersBooks) {
        this.usersBooks = usersBooks;
    }

    public RentPeriod getRent_period() {
        return rent_period;
    }

    public void setRent_period(RentPeriod rent_period) {
        this.rent_period = rent_period;
    }

    public ExtendRentPeriod getExtend_rent_period() {
        return extend_rent_period;
    }

    public void setExtend_rent_period(ExtendRentPeriod extend_rent_period) {
        this.extend_rent_period = extend_rent_period;
    }

    public Boolean getAvailable_for_renting() {
        return available_for_renting;
    }

    public void setAvailable_for_renting(Boolean available_for_renting) {
        this.available_for_renting = available_for_renting;
    }
}

