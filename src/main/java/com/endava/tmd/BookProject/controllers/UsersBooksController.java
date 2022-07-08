package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.UsersBooks;
import com.endava.tmd.BookProject.services.UsersBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users-books")
public class UsersBooksController {

    @Autowired
    public UsersBooksService usersBooksService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UsersBooks> getAllUsersBooks(){
        return usersBooksService.getAllUsersBooks();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create/{user_id}")
    public void createBookWithUserId(@PathVariable Long user_id, @RequestBody Book book){
        usersBooksService.createBookWithUserId(user_id, book);
    }
    @RequestMapping(params = "user_id",method = RequestMethod.GET)
    public List<Book> getUsersBooksByUserId(@RequestParam("user_id") Long user_id){
        return usersBooksService.getUsersBookByUserId(user_id);
    }

}
