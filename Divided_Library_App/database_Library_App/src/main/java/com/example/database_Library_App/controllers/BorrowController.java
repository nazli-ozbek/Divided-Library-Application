package com.example.database_Library_App.controllers;

import com.example.database_Library_App.dto.BorrowRequest;
import com.example.database_Library_App.entities.Book;
import com.example.database_Library_App.entities.Borrow;
import com.example.database_Library_App.entities.Member;
import com.example.database_Library_App.repositories.BookRepository;
import com.example.database_Library_App.repositories.BorrowRepository;
import com.example.database_Library_App.repositories.MemberRepository;
import com.example.database_Library_App.services.BookService;
import com.example.database_Library_App.services.BorrowService;
import com.example.database_Library_App.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/database/borrows")
public class BorrowController {
    private final BorrowService borrowService;
    private  BookService bookService;
    private MemberService memberService;

    @Autowired
    public BorrowController(BorrowService borrowService, BookService bookService, MemberService memberService){
        this.borrowService = borrowService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/getall")
    public List<Borrow> getAllBorrows(){
        try {
            return borrowService.getAllBorrows();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/getborrows")
    public List<Borrow> getBorrows(@RequestBody BorrowRequest borrowRequest) {
        try {
            return borrowService.getBorrows(borrowRequest.getBookId(),borrowRequest.getMemberId(),borrowRequest.getBorrowDate(),borrowRequest.getReturnDate());
        } catch (Exception e) {
           return null;
        }
    }

    @PostMapping("/create")
    public List<Borrow> createBorrow(@RequestBody BorrowRequest borrowRequest ) {
        try {
            Book borrowBook = bookService.getBookByID(borrowRequest.getBookId());
            Member borrowMember = memberService.getMemberByID(borrowRequest.getMemberId());
            Borrow borrow = new Borrow(borrowBook, borrowMember, borrowRequest.getBorrowDate(), borrowRequest.getReturnDate());
            Borrow created = borrowService.createBorrow(borrow);
            List<Borrow> list = new ArrayList<>();
            list.add(created);
            return list;
        } catch (RuntimeException e) {
            System.out.println("Problem with book availability and/or member trustability!");
            return null;
        }
    }

    @DeleteMapping("/delete")
    public List<Borrow> deleteBorrow(@RequestBody BorrowRequest borrowRequest) {
        try {
            Borrow borrow = borrowService.getBorrowByID(borrowRequest.getId()).get();
            List<Borrow> list = new ArrayList<>();
            list.add(borrow);
            borrowService.deleteBorrow(borrowRequest.getId());
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/update")
    public List<Borrow> updateBorrow(@RequestBody BorrowRequest borrowRequest){
        try {
            Borrow updated = borrowService.updateBorrow(borrowRequest.getId(), borrowRequest.getBookId(), borrowRequest.getMemberId(), borrowRequest.getBorrowDate(),borrowRequest.getReturnDate());
            List<Borrow> list = new ArrayList<>();
            list.add(updated);
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}
