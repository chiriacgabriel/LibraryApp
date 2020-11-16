package com.library.controller;

import com.library.model.BookImageUrl;
import com.library.payload.response.MessageResponse;
import com.library.repository.BookImageUrlRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    public ResponseEntity<?> createBookImage(@Valid @RequestBody BookImageUrl bookImageUrl){
        String trimTitle = bookImageUrl.getTitle().trim();
        bookImageUrl.setTitle(trimTitle);

        if (bookImageUrlRepository.existsByTitle(bookImageUrl.getTitle())){
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Title already exists"));
        }

        return ResponseEntity.ok(bookImageUrlRepository.save(bookImageUrl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookImageUrl> deteleBookImageById(@PathVariable int id){
        bookImageUrlRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
