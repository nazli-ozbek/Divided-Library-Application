package com.example.baseRepo_Library_App.controllers;


import com.example.baseRepo_Library_App.dto.BookRequest;
import com.example.baseRepo_Library_App.models.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/base/books")
public class BookController {

    public String requestToString(BookRequest bookRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(bookRequest);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<> getAllBooks(@RequestBody BookRequest bookRequest) {
    try{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8081/api/database/books/getall"))
                .GET()
                .build();
    } catch(Exception e)
    {
        e.getMessage();
    }
}

    @PostMapping("/getbooks")
    public void getBooks(BookRequest bookRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/books/getbooks"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(bookRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @PostMapping("/create")
    public void createBooks(BookRequest bookRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/books/create"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(bookRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @DeleteMapping("/delete")
    public void deleteBooks(BookRequest bookRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/books/delete"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(bookRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @PostMapping("/update")
    public void updateBooks(BookRequest bookRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/books/update"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(bookRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
