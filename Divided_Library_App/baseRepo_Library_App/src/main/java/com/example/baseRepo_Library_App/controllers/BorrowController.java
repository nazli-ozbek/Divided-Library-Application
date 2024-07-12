package com.example.baseRepo_Library_App.controllers;

import com.example.baseRepo_Library_App.dto.BookRequest;
import com.example.baseRepo_Library_App.dto.BookResponse;
import com.example.baseRepo_Library_App.dto.BorrowRequest;
import com.example.baseRepo_Library_App.dto.BorrowResponse;
import com.example.baseRepo_Library_App.models.Book;
import com.example.baseRepo_Library_App.models.Borrow;
import com.example.baseRepo_Library_App.services.BorrowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("api/base/borrows")
public class BorrowController {
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/getall")
    public BorrowResponse getAllBorrows() {
        return borrowService.getAllBorrows();


    }

    @PostMapping("/getborrows")
    public BorrowResponse getBorrows(@RequestBody BorrowRequest borrowRequest) {
        return borrowService.getBorrows(borrowRequest);
    }


    @PostMapping("/create")
    public BorrowResponse createBorrows(@RequestBody BorrowRequest borrowRequest) {
        return borrowService.createBorrows(borrowRequest);
    }

    @DeleteMapping("/delete")
    public BorrowResponse deleteBorrows(@RequestBody BorrowRequest borrowRequest) {
        return borrowService.deleteBorrows(borrowRequest);
    }

    @PutMapping("/update")
    public BorrowResponse updateBorrows(@RequestBody BorrowRequest borrowRequest) {
        return borrowService.updateBorrows(borrowRequest);
    }

    @PostMapping("/order/create")
    public BorrowResponse createBorrow(@RequestBody BorrowRequest borrowRequest) {
        return borrowService.orderCreateBorrows(borrowRequest);
    }
}
