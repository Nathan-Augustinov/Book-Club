package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.User;
import com.endava.tmd.BookProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public Object getUserById(@PathVariable Long userId){
        return userService.getUserById(userId)!=null ? userService.getUserById(userId) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUserById(@RequestParam("userId") Long userId){
        userService.deleteUserById(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestParam("userId") Long userId, @RequestBody User user){
        return userService.updateUser(userId,user);
    }
}
