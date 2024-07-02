package com.example.database_Library_App.controllers;

import com.example.database_Library_App.dto.BorrowRequest;
import com.example.database_Library_App.entities.Borrow;
import com.example.database_Library_App.repositories.BookRepository;
import com.example.database_Library_App.repositories.BorrowRepository;
import com.example.database_Library_App.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/database/borrows")
public class BorrowController {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public BorrowController(BorrowRepository borrowRepository, BookRepository bookRepository, MemberRepository memberRepository){
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/getall")
    public List<Borrow> getAllBorrows() {
        try {
            return borrowRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/getborrows")
    public List<Borrow> getBorrows(@RequestBody BorrowRequest borrowRequest) {
        List<Borrow> borrows = borrowRepository.findAll();
        return borrows.stream()
                .filter(borrow -> (borrowRequest.getBookId() == 0 || Objects.equals(borrow.getBook(),bookRepository.findById(borrowRequest.getBookId()).get())) &&
                        (borrowRequest.getMemberId() == 0 || Objects.equals(borrow.getMember(),memberRepository.findById(borrowRequest.getMemberId()).get())) &&
                        (borrowRequest.getBorrowDate()== null || borrow.getBorrowDate().equals(borrowRequest.getBorrowDate()) &&
                        (borrowRequest.getReturnDate() == null || borrow.getReturnDate().equals(borrowRequest.getReturnDate()))))
                .collect(Collectors.toList());

    }

    @PostMapping("/create")
    public Borrow createBorrow(@RequestBody BorrowRequest borrowRequest) {
        try {
            Borrow borrow = new Borrow(bookRepository.findById(borrowRequest.getBookId()).get(), memberRepository.findById(borrowRequest.getMemberId()).get(), borrowRequest.getBorrowDate(), borrowRequest.getReturnDate());
            return borrowRepository.save(borrow);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @DeleteMapping("/delete")
    public Borrow deleteBorrow(@RequestBody BorrowRequest borrowRequest) {
        try {
            Borrow borrow = borrowRepository.findById(borrowRequest.getId()).get();
            if (borrowRepository.existsById(borrow.getId())) {
                borrowRepository.deleteById(borrow.getId());
            } else {
                throw new RuntimeException("Borrow not found!");
            }
            return borrow;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @PutMapping("/update")
    public Borrow updateBorrow(@RequestBody BorrowRequest borrowRequest) {
        try {
            Borrow newBorrow = new Borrow(bookRepository.findById(borrowRequest.getBookId()).get(), memberRepository.findById(borrowRequest.getMemberId()).get(), borrowRequest.getBorrowDate(), borrowRequest.getReturnDate());
            Optional<Borrow> oldBorrowOptional = borrowRepository.findById(borrowRequest.getId());
            if (oldBorrowOptional.isPresent()) {
                Borrow oldBorrow = oldBorrowOptional.get();
                if (!ObjectUtils.isEmpty(newBorrow.getBook())) {
                    oldBorrow.setBook(newBorrow.getBook());
                }
                if (!ObjectUtils.isEmpty(newBorrow.getMember())) {
                    oldBorrow.setMember(newBorrow.getMember());
                }
                if (!ObjectUtils.isEmpty(newBorrow.getBorrowDate())) {
                    oldBorrow.setBorrowDate(newBorrow.getBorrowDate());
                }
                if (!ObjectUtils.isEmpty(newBorrow.getReturnDate())) {
                    oldBorrow.setReturnDate(newBorrow.getReturnDate());
                }
                return borrowRepository.save(oldBorrow);
            }
            else{return null;}
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }










}
