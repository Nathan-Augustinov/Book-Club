package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.User;
import com.endava.tmd.BookProject.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addUser(User user){
        repository.saveAndFlush(user);
    }

    public void updateUser(Long id, User user){
        User existingUser = repository.findById(id).orElse(null);
        BeanUtils.copyProperties(user, existingUser, "user_id","created_on");
        repository.saveAndFlush(existingUser);
    }
}
