package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.config.SwaggerConfig;
import com.endava.tmd.BookProject.jwt.UsernameAndPasswordAuthenticationRequest;
import com.endava.tmd.BookProject.models.User;
import com.endava.tmd.BookProject.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SwaggerConfig.USER_TAG})
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "Find all users",
            notes = "Returns all users")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @ApiOperation(
            value = "Find user by id",
            notes = "Returns the user with the specified id")
    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.GET)
    public Object getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @ApiOperation(
            value = "Delete user by id",
            notes = "Deletes the user with the specified id")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUserById(@RequestParam("userId") Long userId){
        userService.deleteUserById(userId);
    }

    @ApiOperation(
            value = "Create a new user",
            notes = "Creates a new user account")
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @ApiOperation(
            value = "Log in a user",
            notes = "Log in a user into his account")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest){
        return userService.loginUser(authenticationRequest);
    }

    @ApiOperation(
            value = "Update user by id",
            notes = "Updates the user with the specified id with the details of the request body's user object")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestParam("userId") Long userId, @RequestBody User user){
        return userService.updateUser(userId,user);
    }

    @ApiOperation(
            value = "Verify user authentication token",
            notes = "Verifies the user's authentication token")
    @RequestMapping(value="/verifyToken", method = RequestMethod.GET)
    public ResponseEntity<?> verifyToken(@RequestParam("token") String token){
        return userService.verifyToken(token);
    }
}
