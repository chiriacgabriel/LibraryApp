package com.library.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/books", produces = "application/json")
public class BookRestController {

    private BookRepository bookRepository;

    @Autowired
    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return bookRepository.findById(id)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound()
                                                   .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable int id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok()
                             .build();
    }

    //Create
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        book.getTitle().trim();
        return ResponseEntity.ok(bookRepository.save(book));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable int id,
                                               @RequestBody Book book) {

        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        Book dbBook = optionalBook.get();

        dbBook.setTitle(book.getTitle().trim());
        dbBook.setStock(book.getStock());
        dbBook.setAuthor(book.getAuthor());
        dbBook.setBookImageUrl(book.getBookImageUrl());
        dbBook.setBookCategory(book.getBookCategory());

        if (dbBook.getFictional() != null && book.getNonfictional() != null){
            dbBook.setFictional(null);
            dbBook.setNonfictional(book.getNonfictional());
        }else if (dbBook.getNonfictional() != null && book.getFictional() != null){
            dbBook.setFictional(book.getFictional());
            dbBook.setNonfictional(null);
        }

        if (dbBook.getFictional() != null && book.getFictional() != null){
            dbBook.setFictional(book.getFictional());
        }

        if (dbBook.getNonfictional() != null && book.getNonfictional() != null){
            dbBook.setNonfictional(book.getNonfictional());
        }

        return ResponseEntity.ok(bookRepository.save(dbBook));

    }
}
