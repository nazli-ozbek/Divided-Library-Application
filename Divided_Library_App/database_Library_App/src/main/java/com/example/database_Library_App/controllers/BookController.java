package com.example.database_Library_App.controllers;

import com.example.database_Library_App.dto.BookRequest;
import com.example.database_Library_App.entities.Book;
import com.example.database_Library_App.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/database/books")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/getall")
    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/getbooks")
    public List<Book> getBooks(@RequestBody BookRequest bookRequest) {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .filter(book -> (bookRequest.getName() == null || book.getName().equals(bookRequest.getName())) &&
                        (bookRequest.getAuthor() == null || book.getAuthor().equals(bookRequest.getAuthor())) &&
                        (bookRequest.getPublisher() == null || book.getPublisher().equals(bookRequest.getPublisher())) &&
                        (bookRequest.getIsAvailable() == null || book.getAvailable().equals(bookRequest.getIsAvailable())))
                .collect(Collectors.toList());

    }

    @PostMapping("/create")
    public Book createBook(@RequestBody BookRequest bookRequest) {
        try {
            Book book = new Book(bookRequest.getName(), bookRequest.getAuthor(), bookRequest.getPublisher(), bookRequest.getIsAvailable());
            return bookRepository.save(book);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @DeleteMapping("/delete")
    public Book deleteBook(@RequestBody BookRequest bookRequest) {
        try {
            Book book = bookRepository.findById(bookRequest.getId()).get();
            if (bookRepository.existsById(bookRequest.getId())) {
                bookRepository.deleteById(bookRequest.getId());
            } else {
                throw new RuntimeException("Book not found!");
            }
            return book;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody BookRequest bookRequest) {
        try {
            Book newBook = new Book(bookRequest.getName(), bookRequest.getAuthor(), bookRequest.getPublisher(), bookRequest.getIsAvailable());
            Optional<Book> oldBookOptional = bookRepository.findById(bookRequest.getId());
            if (oldBookOptional.isPresent()) {
                Book oldBook = oldBookOptional.get();
                if (!ObjectUtils.isEmpty(newBook.getName())) {
                    oldBook.setName(newBook.getName());
                }
                if (!ObjectUtils.isEmpty(newBook.getAuthor())) {
                    oldBook.setAuthor(newBook.getAuthor());
                }
                if (!ObjectUtils.isEmpty(newBook.getPublisher())) {
                    oldBook.setPublisher(newBook.getPublisher());
                }
                if (!ObjectUtils.isEmpty(newBook.getAvailable())) {
                    oldBook.setAvailable(newBook.getAvailable());
                }
                return bookRepository.save(oldBook);
            }
            else{return null;}
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
