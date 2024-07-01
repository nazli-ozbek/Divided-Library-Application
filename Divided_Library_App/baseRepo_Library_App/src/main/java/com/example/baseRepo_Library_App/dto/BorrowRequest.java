package com.example.baseRepo_Library_App.dto;

import lombok.Data;
import java.util.Date;

@Data

public class BorrowRequest {
    private int id;
    private int bookId;
    private int memberId;
    private Date borrowDate;
    private Date returnDate;
}
