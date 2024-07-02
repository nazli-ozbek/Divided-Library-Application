package com.example.database_Library_App;

import com.example.database_Library_App.entities.Book;
import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private List<Book> response;

    public BookResponse(List<Book> response){
        this.response = response;
        }
}
