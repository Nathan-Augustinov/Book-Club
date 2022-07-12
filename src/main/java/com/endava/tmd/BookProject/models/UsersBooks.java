package com.endava.tmd.BookProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="users_books_id",
            nullable = false,
            updatable = false)
    private Long usersBooksId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUsersBooksId() {
        return usersBooksId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
