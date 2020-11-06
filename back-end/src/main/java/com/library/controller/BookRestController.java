package com.library.controller;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable int id){
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Create
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.ok(bookRepository.save(book));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable int id,
                                               @RequestBody Book book){

        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Book dbBook = optionalBook.get();

        dbBook.setAuthor(book.getAuthor());
        dbBook.setTitle(book.getTitle());
        dbBook.setTypeOfBook(book.getTypeOfBook());
        dbBook.setStock(book.getStock());
        dbBook.setBookCategory(book.getBookCategory());
        dbBook.setClientSet(book.getClientSet());
        dbBook.setReservationList(book.getReservationList());
        dbBook.setReviewList(book.getReviewList());

        return ResponseEntity.ok(bookRepository.save(dbBook));


    }
}
