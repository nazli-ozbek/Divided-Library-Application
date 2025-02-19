package com.example.database_Library_App.services;

import com.example.database_Library_App.entities.Book;
import com.example.database_Library_App.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    @Service
    public class BookService {

        private final BookRepository bookRepository;

        @Autowired
        public BookService(BookRepository bookRepository){
            this.bookRepository = bookRepository;
        }

        public List<Book> getAllBooks(){
            return bookRepository.findAll();
        }

        public List<Book> getBooks(String name, String author, String publisher, Boolean isAvailable) {
            List<Book> books = bookRepository.findAll();
            return books.stream()
                    .filter(book -> (name == null || book.getName().equals(name)) &&
                            (author == null || book.getAuthor().equals(author)) &&
                            (publisher == null || book.getPublisher().equals(publisher)) &&
                            (isAvailable == null || book.getAvailable().equals(isAvailable)))
                    .collect(Collectors.toList());
        }

        public Book getBookByID(long id){
            return bookRepository.findById(id).get();
        }

        public Book createBook(Book book){
            return bookRepository.save(book);
        }

        public Book updateBook(long id, Book newBook){
            Optional<Book> oldBookOptional = bookRepository.findById(id);
            if(oldBookOptional.isPresent()){
                Book oldBook = oldBookOptional.get();
                if(!ObjectUtils.isEmpty(newBook.getName())){
                    oldBook.setName(newBook.getName());}
                if(!ObjectUtils.isEmpty(newBook.getAuthor())){
                    oldBook.setAuthor(newBook.getAuthor());}
                if(!ObjectUtils.isEmpty(newBook.getPublisher())){
                    oldBook.setPublisher(newBook.getPublisher());}
                if(!ObjectUtils.isEmpty(newBook.getAvailable())){
                    oldBook.setAvailable(newBook.getAvailable());}

                return bookRepository.save(oldBook);
            }
            else{
                throw new RuntimeException("Book not found!");
            }
        }

        public List<Book> deleteBook(long id){
            if(bookRepository.existsById(id)){
                List<Book> list = new ArrayList<>();
                list.add(getBookByID(id));
                bookRepository.deleteById(id);
                return list;
            }
            else{
                throw new RuntimeException("Book not found!");
            }
        }
}
