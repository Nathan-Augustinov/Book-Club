package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.models.UsersBooks;
import com.endava.tmd.BookProject.repositories.BookRepository;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import com.endava.tmd.BookProject.repositories.UsersBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersBooksService {
    @Autowired
    private  UsersBooksRepository usersBooksRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    @Autowired
    private UserService userService;

    public List<UsersBooks> getAllUsersBooks(){
        return usersBooksRepository.findAll();
    }

    public void createBookWithUserId(Long user_id, Book book){
        bookRepository.saveAndFlush(book);
        UsersBooks entry = new UsersBooks(null,userService.getUserById(user_id),book);
        usersBooksRepository.saveAndFlush(entry);
        forRentBookRepository.saveAndFlush(new ForRentBook(null,entry,null,null,true));
    }
}
