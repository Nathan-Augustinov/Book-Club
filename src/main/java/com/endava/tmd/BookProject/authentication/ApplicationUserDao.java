package com.endava.tmd.BookProject.authentication;

import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
