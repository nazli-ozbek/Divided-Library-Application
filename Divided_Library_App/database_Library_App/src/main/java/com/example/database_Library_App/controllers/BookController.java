package com.example.database_Library_App.controllers;

import com.example.database_Library_App.dto.BookRequest;
import com.example.database_Library_App.entities.Book;
import com.example.database_Library_App.repositories.BookRepository;
import com.example.database_Library_App.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/database/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getall")
    public List<Book> getAllBooks(){
        try {
            return bookService.getAllBooks();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getbooks")
    public List<Book> getBooks(@RequestBody BookRequest bookRequest) {
        try {
            return bookService.getBooks(bookRequest.getName(),bookRequest.getAuthor(), bookRequest.getPublisher(),bookRequest.getIsAvailable());
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/create")
    public List<Book> createBook(@RequestBody BookRequest bookRequest) {
        try {
            Book book = new Book(bookRequest.getName(), bookRequest.getAuthor(), bookRequest.getPublisher(), true);
            Book created = bookService.createBook(book);
            List<Book> list = new ArrayList<>();
            list.add(created);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public List<Book> deleteBook(@RequestBody BookRequest bookRequest) {
        try {
            List<Book> list = new ArrayList<>();
            list.add(bookService.getBookByID(bookRequest.getId()));
            bookService.deleteBook(bookRequest.getId());
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/update")
    public List<Book> updateBook(@RequestBody BookRequest bookRequest) {
        try {
            Book newBook = new Book(bookRequest.getName(), bookRequest.getAuthor(), bookRequest.getPublisher(),bookRequest.getIsAvailable());
            Book updated = bookService.updateBook(bookRequest.getId(), newBook);
            List<Book> list = new ArrayList<>();
            list.add(updated);
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}
