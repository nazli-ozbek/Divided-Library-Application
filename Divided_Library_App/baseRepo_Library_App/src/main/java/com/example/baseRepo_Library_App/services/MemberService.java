package com.example.baseRepo_Library_App.services;

import com.example.baseRepo_Library_App.dto.MemberRequest;
import com.example.baseRepo_Library_App.dto.MemberResponse;
import com.example.baseRepo_Library_App.models.Member;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MemberService {

    public MemberResponse getAllMembers(){
        try{
            final String uri = "http://localhost:8083/api/database/members/getall";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Member>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Member>>() {}
            );

            List<Member> result = response.getBody();

            System.out.println(result);

            return new MemberResponse("200",result,"All members.");

        } catch(Exception e)
        {
            e.getMessage();
            return null;
        }
    }

    public MemberResponse getMembers(MemberRequest memberRequest){
        try {
            final String uri = "http://localhost:8083/api/database/members/getmembers";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MemberRequest> requestEntity = new HttpEntity<>(memberRequest);

            ResponseEntity<List<Member>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Member>>() {}
            );

            List<Member> result = response.getBody();

            System.out.println(result);

            return new MemberResponse("200", result, "List of members.");
        } catch (Exception e) {
            e.getMessage();
            return new MemberResponse("500", null, "An error occurred");
        }
    }

    public MemberResponse createMembers(MemberRequest memberRequest){
        try {
            final String uri = "http://localhost:8083/api/database/members/create";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MemberRequest> requestEntity = new HttpEntity<>(memberRequest);

            ResponseEntity<List<Member>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Member>>() {}
            );

            List<Member> result = response.getBody();

            System.out.println(result);

            return new MemberResponse("200", result, "Member created");
        } catch (Exception e) {
            e.getMessage();
            return new MemberResponse("500", null, "An error occurred");
        }
    }

    public MemberResponse deleteMembers(MemberRequest memberRequest){
        final String uri = "http://localhost:8083/api/database/members/delete";
        try{

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MemberRequest> requestEntity = new HttpEntity<>(memberRequest);

            ResponseEntity<List<Member>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
                    requestEntity,
                    new ParameterizedTypeReference<List<Member>>() {}
            );

            List<Member> result = response.getBody();

            System.out.println(result);

            return new MemberResponse("200", result, "Member deleted.");
        } catch (Exception e) {
            e.getMessage();
            return new MemberResponse("500", null, "An error occurred");
        }
    }

    public MemberResponse updateMembers(MemberRequest memberRequest){
        final String uri = "http://localhost:8083/api/database/members/update";
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MemberRequest> requestEntity = new HttpEntity<>(memberRequest);

            ResponseEntity<List<Member>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<List<Member>>() {
                    }
            );

            List<Member> result = response.getBody();

            System.out.println(result);

            return new MemberResponse("200", result, "Member updated.");
        } catch (Exception e) {
            e.getMessage();
            return new MemberResponse("500", null, "error");
        }
    }
}

