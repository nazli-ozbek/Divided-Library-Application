package com.example.baseRepo_Library_App.controllers;

import com.example.baseRepo_Library_App.dto.BorrowRequest;
import com.example.baseRepo_Library_App.models.Book;
import com.example.baseRepo_Library_App.models.Borrow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

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
    public List<Borrow> getAllBorrows() {
        try{
                final String uri = "http://localhost:8083/api/database/borrows/getall";

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Borrow>>() {}
                );

                List<Borrow> result = response.getBody();

                System.out.println(result);

                return result;
        } catch(Exception e)
        {
            e.getMessage();
            return  null;
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
