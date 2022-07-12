package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.UsersBooks;
import com.endava.tmd.BookProject.models.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {
    WaitingList getFirstByUsersBooks(UsersBooks usersBooks);
}
