package com.library.controller;

import com.library.dto.AuthorDto;
import com.library.exception.AuthorAlreadyExistsException;
import com.library.exception.AuthorImageException;
import com.library.exception.AuthorMaxDescriptionException;
import com.library.payload.response.MessageResponse;
import com.library.services.AuthorService;
import com.library.validator.AuthorDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasAnyRole('USER','ADMIN')")
@RequestMapping(value = "/api/authors", produces = "application/json")
public class AuthorRestController {

    private AuthorService authorService;
    private AuthorDtoValidator authorDtoValidator;

    @Autowired
    public AuthorRestController(AuthorService authorService,
                                AuthorDtoValidator authorDtoValidator) {
        this.authorService = authorService;
        this.authorDtoValidator = authorDtoValidator;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable int id) {
        return authorService.findById(id)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound()
                                                     .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> deleteAuthorById(@PathVariable int id) {
        authorService.deleteById(id);

        return ResponseEntity.ok()
                             .build();
    }

    //Create
    @PostMapping()
    public ResponseEntity<?> createAuthor( @RequestBody AuthorDto authorDto) {

        try{
            authorDtoValidator.validate(authorDto);
        }catch (AuthorMaxDescriptionException e){
            return ResponseEntity.badRequest()
                                 .body(new MessageResponse("Description " +
                                         "cannot exceed 2000 characters!"));
        }catch (AuthorAlreadyExistsException e){
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Author " + authorDto.getFirstName() + " " + authorDto.getLastName() + " " + " already registered!"));
        }catch (AuthorImageException e){
            return ResponseEntity.badRequest()
                                 .body(new MessageResponse("Please select " +
                                         "author image!"));
        }

        authorService.addAuthor(authorDto);
        return ResponseEntity.ok().build();

    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> editAuthor(@PathVariable int id,
                                             @RequestBody AuthorDto authorDto) {

        try {
            authorDtoValidator.validate(authorDto, id);
        }catch (AuthorAlreadyExistsException e){
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Author " + authorDto.getFirstName() + " " + authorDto.getLastName() + " " + " already exists!"));
        }

        authorService.update(authorDto, id);

        return ResponseEntity.ok().build();
    }

}
