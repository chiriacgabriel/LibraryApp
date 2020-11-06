package com.library.controller;

import com.library.model.BookImageUrl;
import com.library.repository.BookImageUrlRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/book-image", produces = "application/json")
public class BookImageUrlRestController {

    private BookImageUrlRepository bookImageUrlRepository;

    @Autowired
    public BookImageUrlRestController(BookImageUrlRepository bookImageUrlRepository) {
        this.bookImageUrlRepository = bookImageUrlRepository;
    }

    @GetMapping
    public ResponseEntity<List<BookImageUrl>> getAllBookImage(){
        return ResponseEntity.ok(bookImageUrlRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<BookImageUrl> createBookImage(@RequestBody BookImageUrl bookImageUrl){
        return ResponseEntity.ok(bookImageUrlRepository.save(bookImageUrl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookImageUrl> deteleBookImageById(@PathVariable int id){
        bookImageUrlRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
