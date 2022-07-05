package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Book> getAllUsers(){
        return bookService.getAllBooks();
    }

    @RequestMapping(params = "book_id",method = RequestMethod.GET)
    public Object getUserById(@RequestParam("book_id") Long id){
        return bookService.getBookById(id)!=null ? bookService.getBookById(id) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(params = "book_id", method = RequestMethod.DELETE)
    public void deleteBookById(@RequestParam("book_id") Long id){
        bookService.deleteBookById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @RequestMapping(params = "book_id", method = RequestMethod.PUT)
    public void updateBook(@RequestParam("book_id") Long id, @RequestBody Book book){
        bookService.updateBook(id, book);
    }

    @RequestMapping(value = "/TitleOrAuthor", method = RequestMethod.GET)
    public Book getBookByTitleOrAuthor(@RequestParam(value = "title") Optional<String> title, @RequestParam(value="author") Optional<String> author){
        return bookService.getBookByTitleOrAuthor(title,author);
    }
}
