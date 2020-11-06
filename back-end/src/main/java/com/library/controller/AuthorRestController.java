package com.library.controller;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/authors", produces = "application/json")
public class AuthorRestController {
    
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok(authorRepository.findAll());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id){
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthorById(@PathVariable int id){
        authorRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    //Create
    @PostMapping()
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        return ResponseEntity.ok(authorRepository.save(author));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable int id,
                                             @RequestBody Author author){
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (!optionalAuthor.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Author dbAuthor = optionalAuthor.get();

        dbAuthor.setName(author.getName());
        dbAuthor.setLastName(author.getLastName());
        dbAuthor.setDateOfBirth(author.getDateOfBirth());
        dbAuthor.setNationality(author.getNationality());
        dbAuthor.setBookList(author.getBookList());

        return ResponseEntity.ok(authorRepository.save(dbAuthor));
    }

}
