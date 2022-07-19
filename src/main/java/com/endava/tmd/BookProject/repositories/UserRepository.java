package com.endava.tmd.BookProject.repositories;

import com.endava.tmd.BookProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
