package com.example.database_Library_App.controllers;

import com.example.database_Library_App.entities.Book;
import com.example.database_Library_App.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/database/books")
public class BookController {
    private final BookRepository bookRepository;
    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/getall")
    public List<Book> getAllBooks(){
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
