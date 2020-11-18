package com.library.controller;


import com.library.model.BookCategory;
import com.library.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/book-category", produces = "application/json")
public class BookCategoryRestController {

    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookCategoryRestController(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<BookCategory>> getAllBookCategories() {
        List<BookCategory> sortedBookCategory =
                bookCategoryRepository.findAll();
        sortedBookCategory.sort(Comparator.comparing(BookCategory::getNameOfBookCategory));
        return ResponseEntity.ok(sortedBookCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategory> getAllBookCategoriesById(@PathVariable int id) {
        return bookCategoryRepository.findById(id)
                                     .map(ResponseEntity::ok)
                                     .orElse(ResponseEntity.notFound()
                                                           .build());
    }
}
