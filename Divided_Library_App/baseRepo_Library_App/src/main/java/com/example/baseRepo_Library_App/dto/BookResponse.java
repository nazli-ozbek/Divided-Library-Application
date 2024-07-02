package com.example.baseRepo_Library_App.dto;

import com.example.baseRepo_Library_App.models.Book;
import lombok.Data;

import java.net.http.HttpResponse;
import java.util.List;

@Data
public class BookResponse {
    private String code;
    private String response;
    private String message;

    public BookResponse(String code, String response, String message){
        this.code = code;
        this.response = response;
        this.message = message;
    }
}