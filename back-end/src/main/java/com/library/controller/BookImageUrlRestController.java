package com.library.controller;

import com.library.dto.BookImageUrlDto;
import com.library.exception.BookImageException;
import com.library.payload.response.MessageResponse;
import com.library.services.BookImageUrlService;
import com.library.validator.BookImageUrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/book-image", produces = "application/json")
public class BookImageUrlRestController {

    private BookImageUrlService bookImageUrlService;
    private BookImageUrlValidator bookImageUrlValidator;

    @Autowired
    public BookImageUrlRestController(BookImageUrlService bookImageUrlService, BookImageUrlValidator bookImageUrlValidator) {
        this.bookImageUrlService = bookImageUrlService;
        this.bookImageUrlValidator = bookImageUrlValidator;
    }

    @GetMapping
    public ResponseEntity<List<BookImageUrlDto>> getAllBookImages() {
        return ResponseEntity.ok(bookImageUrlService.getAllBookImages());
    }

    @PostMapping
    public ResponseEntity<?> createBookImage(@Valid @RequestParam("title") String title,
                                             @RequestParam("file") MultipartFile multipartFile) {

        try {
            bookImageUrlValidator.validate(title);
        } catch (BookImageException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Title " + title + " already " +
                            "exists!"));
        }

        bookImageUrlService.addBookImageUrl(title, multipartFile);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookImageUrlDto> deleteBookImageById(@PathVariable int id) {
        bookImageUrlService.deleteBookImageById(id);
        return ResponseEntity.ok().build();
    }

}
