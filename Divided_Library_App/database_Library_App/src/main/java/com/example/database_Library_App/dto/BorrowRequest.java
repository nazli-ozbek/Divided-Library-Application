package com.example.database_Library_App.dto;

import lombok.Data;
import java.util.Date;

@Data

public class BorrowRequest {
    private long id;
    private long bookId;
    private long memberId;
    private Date borrowDate;
    private Date returnDate;
}
