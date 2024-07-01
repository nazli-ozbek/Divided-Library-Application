package com.example.baseRepo_Library_App.models;

import java.util.Date;

public class Borrow {
    private Long id;
    private Book book;
    private Member member;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(Book book, Member member, Date borrowDate, Date returnDate){
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
