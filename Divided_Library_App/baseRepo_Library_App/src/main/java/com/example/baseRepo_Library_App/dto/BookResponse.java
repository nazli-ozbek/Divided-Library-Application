package com.example.baseRepo_Library_App.dto;

import com.example.baseRepo_Library_App.models.Book;
import lombok.Data;

import java.net.http.HttpResponse;
import java.util.List;

@Data
public class BookResponse {
    private String code;
    private List<Book> response;
    private String message;

    public BookResponse(String code, List<Book> books, String message){
        this.code = code;
        this.response = books;
        this.message = message;
    }
}