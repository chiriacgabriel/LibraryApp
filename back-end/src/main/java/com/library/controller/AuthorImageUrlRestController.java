package com.library.controller;

import com.library.model.AuthorImageUrl;
import com.library.repository.AuthorImageUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/author-image", produces = "application/json")
public class AuthorImageUrlRestController {

    private AuthorImageUrlRepository authorImageUrlRepository;

    @Autowired
    public AuthorImageUrlRestController(AuthorImageUrlRepository authorImageUrlRepository) {
        this.authorImageUrlRepository = authorImageUrlRepository;
    }

    @GetMapping
    public ResponseEntity<List<AuthorImageUrl>> getAllImages(){
        return ResponseEntity.ok(authorImageUrlRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "title")));
    }


    @PostMapping
    public ResponseEntity<AuthorImageUrl> createImage(@RequestBody AuthorImageUrl authorImageUrl){
        return ResponseEntity.ok(authorImageUrlRepository.save(authorImageUrl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorImageUrl> deleteImageById(@PathVariable int id){
        authorImageUrlRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
