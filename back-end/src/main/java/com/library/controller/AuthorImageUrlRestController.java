package com.library.controller;

import com.library.dto.AuthorImageUrlDto;
import com.library.exception.AuthorImageException;
import com.library.payload.response.MessageResponse;
import com.library.services.AuthorImageUrlService;
import com.library.validator.AuthorImageUrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/author-image", produces = "application/json")
public class AuthorImageUrlRestController {

    private AuthorImageUrlService authorImageUrlService;
    private AuthorImageUrlValidator authorImageUrlValidator;

    @Autowired
    public AuthorImageUrlRestController(AuthorImageUrlService authorImageUrlService, AuthorImageUrlValidator authorImageUrlValidator) {
        this.authorImageUrlService = authorImageUrlService;
        this.authorImageUrlValidator = authorImageUrlValidator;
    }

    @GetMapping
    public ResponseEntity<List<AuthorImageUrlDto>> getAllImages() {
        return ResponseEntity.ok(authorImageUrlService.getAllImages());
    }


    @PostMapping
    public ResponseEntity<?> createImage(@Valid @RequestParam("title") String title,
                                         @RequestParam("file") MultipartFile multipartFile) {

        try {
            authorImageUrlValidator.validate(title);
        } catch (AuthorImageException e) {
            return ResponseEntity.badRequest()
                                 .body(new MessageResponse(
                                         "Title already exists"));
        }

        authorImageUrlService.addImageUrl(title, multipartFile);
        return ResponseEntity.ok()
                             .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorImageUrlDto> deleteImageById(@PathVariable int id){
        authorImageUrlService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
