package com.example.database_Library_App.services;

import com.example.database_Library_App.entities.Book;
import com.example.database_Library_App.entities.Borrow;
import com.example.database_Library_App.entities.Member;
import com.example.database_Library_App.repositories.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowService {
    final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final MemberService memberService;


    @Autowired
    public BorrowService(BorrowRepository borrowRepository, BookService bookService, MemberService memberService){
        this.borrowRepository = borrowRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public List<Borrow> getAllBorrows(){
        return borrowRepository.findAll();
    }

    public List<Borrow> getBorrows(long bookId, long memberId, Date borrowDate, Date returnDate) {
        List<Borrow> borrows = borrowRepository.findAll();
        return borrows.stream()
                .filter(borrow ->
                        (bookId == 0 || Objects.equals(borrow.getBook(), bookService.getBookByID(bookId))) &&
                                (memberId == 0 || Objects.equals(borrow.getMember(), memberService.getMemberByID(memberId))) &&
                                (borrowDate == null || borrowDate.equals(borrow.getBorrowDate())) &&
                                (returnDate == null || returnDate.equals(borrow.getReturnDate())))
                .collect(Collectors.toList());
    }

    public Optional<Borrow> getBorrowByID(long id){
        return borrowRepository.findById(id);
    }

    public Borrow createBorrow(Borrow borrow){
        if(!borrow.getBook().getAvailable()){
            System.out.println("book!");
            throw new RuntimeException("Book is not available!");
        }
        if(!borrow.getMember().getReliable()){
            System.out.println("member!");
            throw new RuntimeException("Member is not reliable!");
        }
        borrow.getBook().setAvailable(false);
        return borrowRepository.save(borrow);
    }

    public Borrow updateBorrow(long id, long bookId, long memberId, Date borrowDate, Date returnDate){
        Optional <Borrow> oldBorrowOptional = borrowRepository.findById(id);
        if(oldBorrowOptional.isPresent()){
            Borrow oldBorrow = oldBorrowOptional.get();
            if(bookId != 0){
                oldBorrow.setBook(bookService.getBookByID(bookId));}
            if(memberId != 0){
                oldBorrow.setMember(memberService.getMemberByID(memberId));}
            if(borrowDate != null){
                oldBorrow.setBorrowDate(borrowDate);}
            if(returnDate != null){
                oldBorrow.setReturnDate(returnDate);}

            return borrowRepository.save(oldBorrow);
        }
        else{
            throw new RuntimeException("Borrow not found!");
        }
    }

    public void deleteBorrow(long id){
        Optional <Borrow> borrowOptional = borrowRepository.findById(id);
        if(borrowOptional.isPresent()){
            Borrow borrow = borrowOptional.get();
            if (borrow.getBook() != null){
                borrow.getBook().setAvailable(true);}
            borrowRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Borrow not found!");
        }
    }
}
