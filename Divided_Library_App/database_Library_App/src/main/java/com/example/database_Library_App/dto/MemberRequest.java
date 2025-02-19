package com.example.database_Library_App.dto;

import lombok.Data;

@Data
public class MemberRequest {
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private boolean isReliable;
}
