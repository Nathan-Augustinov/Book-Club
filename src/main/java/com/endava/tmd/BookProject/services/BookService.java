package com.endava.tmd.BookProject.services;


import com.endava.tmd.BookProject.models.Book;
import com.endava.tmd.BookProject.models.ForRentBook;
import com.endava.tmd.BookProject.repositories.BookRepository;
import com.endava.tmd.BookProject.repositories.ForRentBookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private ForRentBookRepository forRentBookRepository;

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElse(null);
    }

    public void deleteBookById(Long id){
        repository.deleteById(id);
    }

    public void addBook(Book book){
        repository.saveAndFlush(book);
    }

    public void updateBook(Long id, Book book){
        Book existingBook = repository.findById(id).orElse(null);
        BeanUtils.copyProperties( book, existingBook,"book_id");
        repository.saveAndFlush(existingBook);
    }

    public List<Book> getBooksByTitleOrAuthor(Optional<String> title, Optional<String> author){
        return repository.getBooksByTitleOrAuthor(title,author);
    }


}
