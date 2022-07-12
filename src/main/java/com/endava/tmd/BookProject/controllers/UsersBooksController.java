package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.UsersBooks;
import com.endava.tmd.BookProject.services.UsersBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usersBooks")
public class UsersBooksController {

    @Autowired
    public UsersBooksService usersBooksService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UsersBooks> getAllUsersBooks(){
        return usersBooksService.getAllUsersBooks();
    }

    @RequestMapping(value= "/{userId}",method = RequestMethod.GET)
    public List<Book> getUsersBooksByUserId(@PathVariable Long userId){
        return usersBooksService.getUsersBookByUserId(userId);
    }

}
