package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.User;
import com.endava.tmd.BookProject.repositories.UserRepository;
import com.endava.tmd.BookProject.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Object getUserById(Long userId){
        User user = repository.findById(userId).orElse(null);
        if(user == null){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("");
        }
        return user;
    }

    public void deleteUserById(Long userId){
        repository.deleteById(userId);
    }

    public ResponseEntity<?> addUser(User user){
        List<User> usersList = repository.findAll();
        for (User user1 : usersList) {
            if(user1.getUsername().equals(user.getUsername())){
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Username already exists!");
            }
        }
        user.setPassword(Utils.encodedPassword(user.getPassword()));
        repository.saveAndFlush(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User created successfully");
    }

    public ResponseEntity<?> updateUser(Long userId, User user){
        User existingUser = repository.findById(userId).orElse(null);
        if(existingUser == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User id given not a correct one!");
        }
        BeanUtils.copyProperties(user, existingUser, "userId","createdOn");
        repository.saveAndFlush(existingUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User updated successfully!");
    }
}
