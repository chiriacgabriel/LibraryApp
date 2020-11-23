package com.library.controller;

import com.library.model.Author;
import com.library.payload.response.MessageResponse;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();

        authorList.sort(Comparator.comparing(Author::getName)
                                  .thenComparing(Author::getLastName));

        return ResponseEntity.ok(authorList);
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        return authorRepository.findById(id)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound()
                                                     .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthorById(@PathVariable int id) {
        authorRepository.deleteById(id);

        return ResponseEntity.ok()
                             .build();
    }

    //Create
    @PostMapping()
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        Optional<Author> dbAuthor =
                authorRepository.findByNameAndLastName(author.getName().trim(),
                        author.getLastName().trim());

        if (dbAuthor.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Author " + author.getName() + " " + author.getLastName() +
                    " already exists !"));
        }else {
            return ResponseEntity.ok(authorRepository.save(author));
        }
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> editAuthor(@PathVariable int id,
                                             @RequestBody Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        Optional<Author> dbAuthorCheckDuplicate =
                authorRepository.findByNameAndLastName(author.getName().trim(),
                        author.getLastName().trim());



        if (!optionalAuthor.isPresent()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        if (dbAuthorCheckDuplicate.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Author " + author.getName() + " " + author.getLastName() +
                            " already exists !"));
        }

        Author dbAuthor = optionalAuthor.get();

        dbAuthor.setName(author.getName().trim());
        dbAuthor.setLastName(author.getLastName().trim());
        dbAuthor.setDateOfBirth(author.getDateOfBirth());
        dbAuthor.setNationality(author.getNationality().trim());
        dbAuthor.setType(author.getType().trim());
        dbAuthor.setAuthorImageUrl(author.getAuthorImageUrl());
        dbAuthor.setDescription(author.getDescription().trim());

        return ResponseEntity.ok(authorRepository.save(dbAuthor));
    }

}
