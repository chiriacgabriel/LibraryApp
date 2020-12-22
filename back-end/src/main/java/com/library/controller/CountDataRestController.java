package com.library.controller;

import com.library.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping(value = "/api/count-data", produces = "application/json")
@RestController
public class CountDataRestController {

    private AuthorService authorService;
    private UserDetailsServiceImpl userDetailsService;
    private BookService bookService;
    private ReservationService reservationService;

    @Autowired
    public CountDataRestController(AuthorService authorService, UserDetailsServiceImpl userDetailsService, BookService bookService, ReservationService reservationService) {
        this.authorService = authorService;
        this.userDetailsService = userDetailsService;
        this.bookService = bookService;
        this.reservationService = reservationService;
    }

    @GetMapping("/author")
    public ResponseEntity<Integer> getAllCountAuthor(){
        return ResponseEntity.ok(authorService.countAllAuthors());
    }

    @GetMapping("/user")
    public ResponseEntity<Integer> getAllCountUsers(){
        return ResponseEntity.ok(userDetailsService.countAllUsers());
    }

    @GetMapping("/book")
    public ResponseEntity<Integer> getAllCountBooks(){
        return ResponseEntity.ok(bookService.countAllBooks());
    }

    @GetMapping("/reservation")
    public ResponseEntity<Integer> getAllCountReservations(){
        return ResponseEntity.ok(reservationService.countAllReservations());
    }
}
