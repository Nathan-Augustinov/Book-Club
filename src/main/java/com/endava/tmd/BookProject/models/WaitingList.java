package com.endava.tmd.BookProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "waiting_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="waiting_list_id", nullable = false, updatable = false)
    private Long waitingListId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "users_books_id")
    private UsersBooks usersBooks;

    public User getUser() {
        return user;
    }

    public Long getWaitingListId() {
        return waitingListId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UsersBooks getUsersBooks() {
        return usersBooks;
    }

    public void setUsersBooks(UsersBooks usersBooks) {
        this.usersBooks = usersBooks;
    }
}
