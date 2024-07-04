package com.example.database_Library_App.controllers;

import com.example.database_Library_App.dto.MemberRequest;
import com.example.database_Library_App.entities.Member;
import com.example.database_Library_App.repositories.MemberRepository;
import com.example.database_Library_App.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/database/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/getall")
    public List<Member> getAllMembers(){
        try {
            return memberService.getAllMembers();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/getmembers")
    public List<Member> getMembers(@RequestBody MemberRequest memberRequest) {
        try {
            return memberService.getMembers(memberRequest.getName(), memberRequest.getSurname(), memberRequest.getPhoneNumber());
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/create")
    public List<Member> createMember(@RequestBody MemberRequest memberRequest) {
        try {
            Member member = new Member(memberRequest.getName(),memberRequest.getSurname(), memberRequest.getPhoneNumber());
            Member created = memberService.createMember(member);
            List<Member> list = new ArrayList<>();
            list.add(created);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public List<Member> deleteMember(@RequestBody MemberRequest memberRequest) {
        try {
            List<Member> list = new ArrayList<>();
            list.add(memberService.getMemberByID(memberRequest.getId()));
            memberService.deleteMember(memberRequest.getId());
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/update")
    public List<Member> updateMember(@RequestBody MemberRequest memberRequest) {
        try {
            Member newMember = new Member(memberRequest.getName(),memberRequest.getSurname(), memberRequest.getPhoneNumber());
            Member updated = memberService.updateMember(memberRequest.getId(),newMember);
            List<Member> list = new ArrayList<>();
            list.add(updated);
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
