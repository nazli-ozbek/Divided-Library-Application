package com.example.baseRepo_Library_App.services;

import com.example.baseRepo_Library_App.dto.BorrowRequest;
import com.example.baseRepo_Library_App.dto.BorrowResponse;
import com.example.baseRepo_Library_App.models.Borrow;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BorrowService {

    public BorrowResponse getAllBorrows(){
        try{
            final String uri = "http://localhost:8083/api/database/borrows/getall";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Borrow>>() {}
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200",result,"All borrows");

        } catch(Exception e)
        {
            e.getMessage();
            return null;
        }
    }

    public BorrowResponse getBorrows(BorrowRequest borrowRequest){
        try {
            final String uri = "http://localhost:8083/api/database/borrows/getborrows";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {}
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "List of borrows.");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "An error occurred");
        }
    }

    public BorrowResponse createBorrows(BorrowRequest borrowRequest){
        try {
            final String uri = "http://localhost:8083/api/database/borrows/create";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {
                    }
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "Borrow created");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "An error occurred");

        }
    }

    public BorrowResponse deleteBorrows(BorrowRequest borrowRequest){
        final String uri = "http://localhost:8083/api/database/borrows/delete";
        try{

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {}
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "Borrow deleted.");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "An error occurred");
        }
    }

    public BorrowResponse updateBorrows(BorrowRequest borrowRequest){
        final String uri = "http://localhost:8083/api/database/borrows/update";
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {
                    }
            );

            List<Borrow> result = response.getBody();

            System.out.println(result);

            return new BorrowResponse("200", result, "Borrow updated.");
        } catch (Exception e) {
            e.getMessage();
            return new BorrowResponse("500", null, "error");
        }
    }

    public BorrowResponse orderCreateBorrows(BorrowRequest borrowRequest){
        try {
            final String uri = "http://localhost:8080/api/order/borrows/create";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BorrowRequest> requestEntity = new HttpEntity<>(borrowRequest);

            ResponseEntity<List<Borrow>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Borrow>>() {}
            );

            List<Borrow> result = response.getBody();

            if(!ObjectUtils.isEmpty(result)) {
                return new BorrowResponse("200", result, "Borrow created.");
            } else {
                return new BorrowResponse("204", null, "Check book availability & member trustability.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new BorrowResponse("500", null, "An error occurred: " + e.getMessage());
        }
    }
    }


