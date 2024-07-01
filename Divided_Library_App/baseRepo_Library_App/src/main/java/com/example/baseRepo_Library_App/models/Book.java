package com.example.baseRepo_Library_App.models;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String name;
    private String author;
    private String publisher;
    private Boolean isAvailable;


    public Book(String name, String author, String publisher, Boolean isAvailable) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.isAvailable = isAvailable;
    }
}
