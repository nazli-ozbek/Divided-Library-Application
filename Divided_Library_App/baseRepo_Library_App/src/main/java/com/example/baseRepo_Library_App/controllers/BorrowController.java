package com.example.baseRepo_Library_App.controllers;

import com.example.baseRepo_Library_App.dto.BookRequest;
import com.example.baseRepo_Library_App.dto.BookResponse;
import com.example.baseRepo_Library_App.dto.BorrowRequest;
import com.example.baseRepo_Library_App.dto.BorrowResponse;
import com.example.baseRepo_Library_App.models.Book;
import com.example.baseRepo_Library_App.models.Borrow;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("api/base/borrows")
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
    public BorrowResponse getAllBorrows() {
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

            return new BorrowResponse("200",result,"All borrows");

        } catch(Exception e)
        {
            e.getMessage();
            return null;
        }
    }

    @PostMapping("/getborrows")
    public BorrowResponse getBorrows(@RequestBody BorrowRequest borrowRequest) {
        try {
            final String uri = "http://localhost:8083/api/database/borrows/getborrows";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {}
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "List of borrows.");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "An error occurred");
        }
    }


    @PostMapping("/create")
    public BorrowResponse createBorrows(@RequestBody BorrowRequest borrowRequest) {
        try {
            final String uri = "http://localhost:8083/api/database/borrows/create";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {}
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "Borrow created");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "An error occurred");
        }
    }

    @DeleteMapping("/delete")
    public BorrowResponse deleteBorrows(@RequestBody BorrowRequest borrowRequest) {
        final String uri = "http://localhost:8083/api/database/borrows/delete";
        try{

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {}
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "Borrow deleted.");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "An error occurred");
        }
    }

    @PutMapping("/update")
    public BorrowResponse updateBorrows(@RequestBody BorrowRequest borrowRequest) {
        final String uri = "http://localhost:8083/api/database/borrows/update";
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {
                    }
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "Borrow updated.");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "error");
        }
    }
}
