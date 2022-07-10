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

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object getUserById(@PathVariable Long id){
        return userService.getUserById(id)!=null ? userService.getUserById(id) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(params = "user_id", method = RequestMethod.DELETE)
    public void deleteUserById(@RequestParam("user_id") Long id){
        userService.deleteUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(params = "user_id",method = RequestMethod.PUT)
    public void updateUser(@RequestParam("user_id") Long id, @RequestBody User user){
        userService.updateUser(id,user);
    }
}
