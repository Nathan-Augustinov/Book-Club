package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.User;
import com.endava.tmd.BookProject.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        return repository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id){
        repository.deleteById(id);
    }

    public String encodedPassword(String plainPassword){
        int strength = 10;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = encoder.encode(plainPassword);
        return encodedPassword;
    }

    public ResponseEntity<?> addUser(User user){
        List<User> usersList = repository.findAll();
        for (User user1 : usersList) {
            if(user1.getUsername().equals(user.getUsername())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists!");
            }
        }
        user.setPassword(encodedPassword(user.getPassword()));
        repository.saveAndFlush(user);
        return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
    }

    public void updateUser(Long id, User user){
        User existingUser = repository.findById(id).orElse(null);
        BeanUtils.copyProperties(user, existingUser, "user_id","created_on");
        repository.saveAndFlush(existingUser);
    }
}
