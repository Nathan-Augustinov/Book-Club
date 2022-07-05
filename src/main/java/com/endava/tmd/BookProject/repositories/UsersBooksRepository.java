package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.UsersBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersBooksRepository extends JpaRepository<UsersBooks, Long> {
}
