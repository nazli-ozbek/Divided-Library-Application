package com.example.database_Library_App.controllers;

import com.example.database_Library_App.dto.MemberRequest;
import com.example.database_Library_App.entities.Member;
import com.example.database_Library_App.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/database/members")
public class MemberController {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping("/getall")
    public List<Member> getAllMembers() {
        try {
            return memberRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/getmembers")
    public List<Member> getMembers(@RequestBody MemberRequest memberRequest) {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .filter(member -> (memberRequest.getName() == null || member.getName().equals(memberRequest.getName())) &&
                        (memberRequest.getSurname() == null || member.getSurname().equals(memberRequest.getSurname())) &&
                        (memberRequest.getPhoneNumber() == null || member.getPhoneNumber().equals(memberRequest.getPhoneNumber())))
                .collect(Collectors.toList());

    }

    @PostMapping("/create")
    public Member createMember(@RequestBody MemberRequest memberRequest) {
        try {
            Member member = new Member(memberRequest.getName(), memberRequest.getSurname(), memberRequest.getPhoneNumber());
            return memberRepository.save(member);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @DeleteMapping("/delete")
    public Member deleteMember(@RequestBody MemberRequest memberRequest) {
        try {
            Member member = memberRepository.findById(memberRequest.getId()).get();
            if (memberRepository.existsById(memberRequest.getId())) {
                memberRepository.deleteById(memberRequest.getId());
            } else {
                throw new RuntimeException(" Member not found!");
            }
            return member;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @PutMapping("/update")
    public Member updateMember(@RequestBody MemberRequest memberRequest) {
        try {
            Member newMember = new Member(memberRequest.getName(), memberRequest.getSurname(), memberRequest.getPhoneNumber());
            Optional<Member> oldMemberOptional = memberRepository.findById(memberRequest.getId());
            if (oldMemberOptional.isPresent()) {
                Member oldMember = oldMemberOptional.get();
                if (!ObjectUtils.isEmpty(newMember.getName())) {
                    oldMember.setName(newMember.getName());
                }
                if (!ObjectUtils.isEmpty(newMember.getSurname())) {
                    oldMember.setSurname(newMember.getSurname());
                }
                if (!ObjectUtils.isEmpty(newMember.getPhoneNumber())) {
                    oldMember.setPhoneNumber(newMember.getPhoneNumber());
                }
                return memberRepository.save(oldMember);
            }
            else{return null;}
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }



}
