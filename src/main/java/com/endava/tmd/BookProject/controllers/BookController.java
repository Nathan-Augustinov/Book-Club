package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    public BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    
    @RequestMapping(
            value = "/{bookId}",
            method = RequestMethod.GET)
    public Object getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBookById(@RequestParam("bookId") Long bookId){
        bookService.deleteBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestParam("bookId") Long bookId, @RequestBody Book book){
        return bookService.updateBook(bookId, book);
    }

    @RequestMapping(
            value = "/titleOrAuthor",
            method = RequestMethod.GET)
    public List<Book> getBooksByTitleOrAuthor(@RequestParam(value = "title") Optional<String> title, @RequestParam(value="author") Optional<String> author){
        return bookService.getBooksByTitleOrAuthor(title,author);
    }

    @RequestMapping(
            value = "/addBookByUserId",
            method = RequestMethod.POST)
    public ResponseEntity<?> createBookWithUserId(@RequestParam(value= "userId") Long userId, @RequestBody Book book){
        return bookService.createBookWithUserId(userId, book);
    }

}
