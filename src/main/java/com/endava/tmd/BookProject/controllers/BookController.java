package com.endava.tmd.BookProject.controllers;

import com.endava.tmd.BookProject.config.SwaggerConfig;
import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {SwaggerConfig.BOOK_TAG})
@RequestMapping("api/books")
public class BookController {

    @Autowired
    public BookService bookService;

    @ApiOperation(
            value = "Find all books",
            notes = "Returns all books")
    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @ApiOperation(
            value = "Find book by id",
            notes = "Returns the book with the specified id")
    @RequestMapping(
            value = "/{bookId}",
            method = RequestMethod.GET)
    public Object getBookById(@PathVariable @ApiParam(name="bookId", value="Book's id", example = "1") Long bookId){
        return bookService.getBookById(bookId);
    }

    @ApiOperation(
            value = "Delete book by id",
            notes = "Deletes the book with the specified id")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBookById(@RequestParam("bookId") @ApiParam(name="bookId", value="Book's id", example = "1") Long bookId){
        bookService.deleteBookById(bookId);
    }

    @ApiOperation(
            value = "Create a new book",
            notes = "Creates a new book")
    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @ApiOperation(
            value = "Update book by id",
            notes = "Updates the book with the specified id with the details of the request body's book object")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestParam("bookId") @ApiParam(name="bookId", value="Book's id", example = "1") Long bookId, @RequestBody Book book){
        return bookService.updateBook(bookId, book);
    }

    @ApiOperation(
            value = "Find books by title or author",
            notes = "Returns the books with the title or author specified")
    @RequestMapping(
            value = "/titleOrAuthor/{searchInput}",
            method = RequestMethod.GET)
    public List<Book> getBooksByTitleOrAuthor(@PathVariable @ApiParam(name="titleOrAuthor", value="Title or author search input", example = "baltagul") String searchInput){
        return bookService.getBooksByTitleOrAuthor(searchInput);
    }



    @ApiOperation(
            value = "A user can create a book",
            notes = "The user with the id specified creates a new book with the request body contents")
    @RequestMapping(
            value = "/addBookByUserId",
            method = RequestMethod.POST)
    public ResponseEntity<?> createBookWithUserId(@RequestParam(value= "userId") @ApiParam(name="userId", value="User's id", example = "1")Long userId, @RequestBody Book book){
        return bookService.createBookWithUserId(userId, book);
    }

    @ApiOperation(
            value = "Find all books owned by a user",
            notes = "Returns all books owned by the user with the specified id")
    @RequestMapping(
            value= "/getBooksByUserId/{userId}",
            method = RequestMethod.GET)
    public List<Book> getBooksByUserId(@PathVariable @ApiParam(name="userId", value="User's id", example = "1") Long userId){
        return bookService.getBooksByUserId(userId);
    }

}
