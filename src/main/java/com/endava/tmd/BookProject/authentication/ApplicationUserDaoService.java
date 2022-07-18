package com.endava.tmd.BookProject.authentication;

import com.endava.tmd.BookProject.models.User;
import com.endava.tmd.BookProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public class ApplicationUserDaoService implements ApplicationUserDao {

    @Autowired
    private UserService userService;
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUserList = new ArrayList<>();
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            ApplicationUser applicationUser = new ApplicationUser(
                    user.getUsername(),
                    user.getPassword(),
                    Set.of(),
                    true,
                    true,
                    true,
                    true
            );
            applicationUserList.add(applicationUser);
        }
        return applicationUserList;
    }
}
