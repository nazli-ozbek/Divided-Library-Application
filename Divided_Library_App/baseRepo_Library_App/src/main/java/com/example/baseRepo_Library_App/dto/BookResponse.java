package com.example.baseRepo_Library_App.dto;

import com.example.baseRepo_Library_App.models.Book;
import lombok.Data;
import java.util.List;

@Data
public class BookResponse {
    private String code;
    private List<Book> books;
    private String message;

    public BookResponse(String code, List<Book> books, String message){
        this.code = code;
        this.books = books;
        this.message = message;
    }
}