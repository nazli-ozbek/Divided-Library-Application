package com.example.baseRepo_Library_App.controllers;


import com.example.baseRepo_Library_App.dto.BookRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("api/base/books")
public class BookController {

    public String requestToString(BookRequest bookRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        return null;//objectMapper.writeValueAsString(bookRequest);
    }

    @GetMapping("/getall")
    public void getAllBooks(BookRequest bookRequest) {
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://postman-echo.com/get"))
                    .GET()
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
