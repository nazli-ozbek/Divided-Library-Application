package com.example.baseRepo_Library_App.models;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private boolean reliable;


    public Member(String name, String surname, String phoneNumber, boolean reliable){
        this.name = name;
        this.surname = surname;
        this. phoneNumber = phoneNumber;
        this.reliable = reliable;
    }

}
