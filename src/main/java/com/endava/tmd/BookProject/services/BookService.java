package com.endava.tmd.BookProject.services;


import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.models.UsersBooks;
import com.endava.tmd.BookProject.repositories.BookRepository;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import com.endava.tmd.BookProject.repositories.UsersBooksRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UsersBooksRepository usersBooksRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId){
        return bookRepository.findById(bookId).orElse(null);
    }

    public void deleteBookById(Long bookId){
        bookRepository.deleteById(bookId);
    }

    public void addBook(Book book){
        bookRepository.saveAndFlush(book);
    }

    public ResponseEntity<?> updateBook(Long bookId, Book book){
        Book existingBook = bookRepository.findById(bookId).orElse(null);
        if(existingBook == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book id given is not a correct one!");
        }
        BeanUtils.copyProperties( book, existingBook,"book_id");
        bookRepository.saveAndFlush(existingBook);
        return ResponseEntity.status(HttpStatus.OK).body("Book successfully updated!");
    }

    public List<Book> getBooksByTitleOrAuthor(Optional<String> title, Optional<String> author){
        return bookRepository.getBooksByTitleOrAuthor(title,author);
    }

    public ResponseEntity<?> createBookWithUserId(Long userId, Book book){
        if(!checkIfSameUserAlreadyAddedSameBook(userId, book)){
            bookRepository.saveAndFlush(book);
            UsersBooks entry = new UsersBooks(null,userService.getUserById(userId),book);
            usersBooksRepository.saveAndFlush(entry);
            forRentBookRepository.saveAndFlush(new ForRentBook(null,entry,null,null,true));
            return ResponseEntity.status(HttpStatus.OK).body("The user's book has been successfully added!");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The same user has already added the same book!");
        }

    }

    public boolean checkIfSameUserAlreadyAddedSameBook(Long userID, Book book){
        List<UsersBooks> usersBooksList = usersBooksRepository.findAll();
        for (UsersBooks usersBooks : usersBooksList) {
            if(usersBooks.getUser().getUserId().equals(userID) && usersBooks.getBook().getAuthor().equals(book.getAuthor()) && usersBooks.getBook().getTitle().equals(book.getTitle())){
                return true;
            }
        }
        return  false;
    }


}
