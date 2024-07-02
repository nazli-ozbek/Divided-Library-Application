package com.example.baseRepo_Library_App.controllers;

import com.example.baseRepo_Library_App.dto.BorrowRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.net.http.HttpRequest;

public class BorrowController {
    public String requestToString(BorrowRequest borrowRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(borrowRequest);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getall")
    public void getAllBorrows() {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/borrows/getall"))
                    .GET()
                    .build();
        } catch(Exception e)
        {
            e.getMessage();
        }
    }

    @PostMapping("/getborrows")
    public void getBorrows(BorrowRequest borrowRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/borrows/getborrows"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(borrowRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @PostMapping("/create")
    public void createBorrows(BorrowRequest borrowRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/borrows/create"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(borrowRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @DeleteMapping("/delete")
    public void deleteBorrows(BorrowRequest borrowRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/borrows/delete"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(borrowRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @PostMapping("/update")
    public void updateBorrows(BorrowRequest borrowRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/borrows/update"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(borrowRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
