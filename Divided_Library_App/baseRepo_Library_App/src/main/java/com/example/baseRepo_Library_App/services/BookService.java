package com.example.baseRepo_Library_App.services;

import com.example.baseRepo_Library_App.dto.BookRequest;
import com.example.baseRepo_Library_App.dto.BookResponse;
import com.example.baseRepo_Library_App.models.Book;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {

    public BookResponse getAllBooks() {
        try {
            final String uri = "http://localhost:8083/api/database/books/getall";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();

            System.out.println(result);

            return new BookResponse("200", result, "All books");

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public BookResponse getBooks(BookRequest bookRequest) {
        try {
            final String uri = "http://localhost:8083/api/database/books/getbooks";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BookRequest> requestEntity = new HttpEntity<>(bookRequest);

            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();

            System.out.println(result);

            return new BookResponse("200", result, "List of books.");
        } catch (Exception e) {
            e.getMessage();
            return new BookResponse("500", null, "An error occurred");
        }
    }

    public BookResponse createBooks(BookRequest bookRequest) {
        try {
            final String uri = "http://localhost:8083/api/database/books/create";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BookRequest> requestEntity = new HttpEntity<>(bookRequest);

            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();

            System.out.println(result);

            return new BookResponse("200", result, "Book created");
        } catch (Exception e) {
            e.getMessage();
            return new BookResponse("500", null, "An error occurred");
        }
    }

    public BookResponse deleteBooks(BookRequest bookRequest) {
        final String uri = "http://localhost:8083/api/database/books/delete";
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BookRequest> requestEntity = new HttpEntity<>(bookRequest);

            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
                    requestEntity,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();

            System.out.println(result);

            return new BookResponse("200", result, "Book deleted.");
        } catch (Exception e) {
            e.getMessage();
            return new BookResponse("500", null, "An error occurred");
        }
    }

    public BookResponse updateBooks(BookRequest bookRequest) {
        final String uri = "http://localhost:8083/api/database/books/update";
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BookRequest> requestEntity = new HttpEntity<>(bookRequest);

            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();

            System.out.println(result);

            return new BookResponse("200", result, "Book updated.");
        } catch (Exception e) {
            e.getMessage();
            return new BookResponse("500", null, "error");
        }
    }

    public BookResponse bookOrderGetAll() {
        try {
            final String uri = "http://localhost:8080/api/order/books/getall";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();

            System.out.println(result);

            return new BookResponse("200", result, "All books");

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public BookResponse bookOrderGetBooks(BookRequest bookRequest) {
        try {
            final String uri = "http://localhost:8080/api/order/books/getbooks";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BookRequest> requestEntity = new HttpEntity<>(bookRequest);

            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();

            System.out.println(result);


            return new BookResponse("200", result, "List of books.");
        } catch (Exception e) {
            e.getMessage();
            return new BookResponse("500", null, "An error occurred");
        }
    }
}
