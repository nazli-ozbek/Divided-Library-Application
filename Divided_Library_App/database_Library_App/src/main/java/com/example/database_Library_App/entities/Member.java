package com.example.database_Library_App.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "members")
@NoArgsConstructor
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "reliable")
    private Boolean reliable;

    @OneToMany(mappedBy = "member")
    private List<Borrow> borrows;

    public Member(String name, String surname, String phoneNumber, boolean reliable){
        this.name = name;
        this.surname = surname;
        this. phoneNumber = phoneNumber;
        this.reliable = reliable;
    }

    public Member(String name, String surname, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this. phoneNumber = phoneNumber;
        this.reliable = true;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getReliable() {
        return reliable;
    }

    public void setReliable(Boolean reliable) {
        this.reliable = reliable;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
