package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.config.SwaggerConfig;
import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.UsersBooks;
import com.endava.tmd.BookProject.services.UsersBooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SwaggerConfig.USER_BOOK_TAG})
@RequestMapping("api/usersBooks")
public class UsersBooksController {

    @Autowired
    public UsersBooksService usersBooksService;

    @ApiOperation(
            value = "Find all books and their owners",
            notes = "Returns all books and their owners")
    @RequestMapping(method = RequestMethod.GET)
    public List<UsersBooks> getAllUsersBooks(){
        return usersBooksService.getAllUsersBooks();
    }

}
