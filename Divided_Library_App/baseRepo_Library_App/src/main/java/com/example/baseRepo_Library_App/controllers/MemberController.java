package com.example.baseRepo_Library_App.controllers;

import com.example.baseRepo_Library_App.dto.MemberRequest;
import com.example.baseRepo_Library_App.dto.MemberResponse;
import com.example.baseRepo_Library_App.models.Member;
import com.example.baseRepo_Library_App.services.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("api/base/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/getall")
    public MemberResponse getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("/getmembers")
    public MemberResponse getMembers(@RequestBody MemberRequest memberRequest) {
        return memberService.getMembers(memberRequest);
    }

    @PostMapping("/create")
    public MemberResponse createMembers(@RequestBody MemberRequest memberRequest) {
        return memberService.createMembers(memberRequest);
    }

    @DeleteMapping("/delete")
    public MemberResponse deleteMembers(@RequestBody MemberRequest memberRequest) {
        return memberService.deleteMembers(memberRequest);
    }

    @PutMapping("/update")
    public MemberResponse updateMembers(@RequestBody MemberRequest memberRequest) {
        return memberService.updateMembers(memberRequest);

    }
}
