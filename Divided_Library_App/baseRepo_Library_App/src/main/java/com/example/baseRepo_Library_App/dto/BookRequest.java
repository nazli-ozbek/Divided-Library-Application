package com.example.baseRepo_Library_App.dto;

import lombok.Data;


@Data
public class BookRequest {
    private int id;
    private String name;
    private String author;
    private String publisher;
    private Boolean isAvailable;
}