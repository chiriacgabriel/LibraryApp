package com.library.controller;

import com.library.dto.BookDto;
import com.library.exception.BookNoAuthorException;
import com.library.exception.BookNoCategoryException;
import com.library.exception.BookNoTypeSelectedException;
import com.library.payload.response.MessageResponse;
import com.library.services.BookService;
import com.library.validator.BookDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/books", produces = "application/json")
public class BookRestController {

    private BookService bookService;
    private BookDtoValidator bookDtoValidator;

    @Autowired
    public BookRestController(BookService bookService, BookDtoValidator bookDtoValidator) {
        this.bookService = bookService;
        this.bookDtoValidator = bookDtoValidator;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable int id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable int id) {
        bookService.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }

    //Create
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDto bookDto) {
        bookDto.getTitle().trim();

        try {
            bookDtoValidator.validate(bookDto);
        } catch (BookNoAuthorException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "No Author is selected, please select one!"));
        } catch (BookNoCategoryException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("No " +
                    "category is selected, please select one!"));
        } catch (BookNoTypeSelectedException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("No " +
                    "type selected, please select one!"));
        }

        bookService.addBook(bookDto);
        return ResponseEntity.ok().build();
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBookById(@PathVariable int id,
                                                  @RequestBody BookDto bookDto) {

        bookService.update(id, bookDto);
        return ResponseEntity.ok().build();
    }
}
