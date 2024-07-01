package com.example.baseRepo_Library_App.dto;

import com.example.baseRepo_Library_App.models.Borrow;
import lombok.Data;
import java.util.List;

@Data
public class BorrowResponse {
    private String code;
    private List<Borrow> borrows;
    private String message;

    public BorrowResponse(String code, List<Borrow> borrows, String message){
        this.code = code;
        this.borrows = borrows;
        this.message = message;
    }
}