package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.UsersBooks;
import com.endava.tmd.BookProject.repositories.UsersBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersBooksService {
    @Autowired
    private  UsersBooksRepository usersBooksRepository;

    public List<UsersBooks> getAllUsersBooks(){
        return usersBooksRepository.findAll();
    }

    public List<Book> getUsersBookByUserId(Long userId){
        return usersBooksRepository.getUsersBooksByUserId(userId);
    }
}
