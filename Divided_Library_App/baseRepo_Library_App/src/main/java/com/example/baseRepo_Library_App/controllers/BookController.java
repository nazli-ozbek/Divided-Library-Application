package com.example.baseRepo_Library_App.controllers;


import com.example.baseRepo_Library_App.dto.BookRequest;
import com.example.baseRepo_Library_App.dto.BookResponse;
import com.example.baseRepo_Library_App.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/base/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getall")
    public BookResponse getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/getbooks")
    public BookResponse getBooks(@RequestBody BookRequest bookRequest) {
        return bookService.getBooks(bookRequest);
    }

    @PostMapping("/create")
    public BookResponse createBooks(@RequestBody BookRequest bookRequest) {
        return bookService.createBooks(bookRequest);
    }

    @DeleteMapping("/delete")
    public BookResponse deleteBooks(@RequestBody BookRequest bookRequest) {
        return bookService.deleteBooks(bookRequest);
    }

    @PutMapping("/update")
    public BookResponse updateBooks(@RequestBody BookRequest bookRequest) {
        return bookService.updateBooks(bookRequest);
    }

    @GetMapping("/order/getall")
    public BookResponse orderGetAll() {
        return bookService.bookOrderGetAll();
    }

    @PostMapping("/order/getbooks")
    public BookResponse orderGetBooks(@RequestBody BookRequest bookRequest) {
        return bookService.bookOrderGetBooks(bookRequest);
    }
}
