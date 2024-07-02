package com.example.baseRepo_Library_App.controllers;

import com.example.baseRepo_Library_App.dto.MemberRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("api/base/members")
public class MemberController {
    public String requestToString(MemberRequest memberRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(memberRequest);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getall")
    public void getAllMembers() {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/members/getall"))
                    .GET()
                    .build();
        } catch(Exception e)
        {
            e.getMessage();
        }
    }

    @PostMapping("/getmembers")
    public void getMembers(MemberRequest memberRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/members/getmembers"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(memberRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @PostMapping("/create")
    public void createMembers(MemberRequest memberRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/members/create"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(memberRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @DeleteMapping("/delete")
    public void deleteMembers(MemberRequest memberRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/members/delete"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(memberRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @PostMapping("/update")
    public void updateMembers(MemberRequest memberRequest) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8081/api/database/members/update"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestToString(memberRequest)))
                    .build();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
