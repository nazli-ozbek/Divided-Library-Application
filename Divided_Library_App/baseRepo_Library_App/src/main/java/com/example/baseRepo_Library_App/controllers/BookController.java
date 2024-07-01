package com.example.baseRepo_Library_App.controllers;


import com.example.baseRepo_Library_App.dto.BookRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpRequest;

@RestController
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
    public void getAllBooks() {
    try{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8081/books/getall"))
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
                    .uri(new URI("http://localhost:8081/books/getbooks"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(bookRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
