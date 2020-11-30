package com.library.controller;


import com.library.dto.BookCategoryDto;
import com.library.services.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/book-category", produces = "application/json")
public class BookCategoryRestController {

    private BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryRestController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<BookCategoryDto>> getAllBookCategories() {
        return ResponseEntity.ok(bookCategoryService.getAllBookCategories());
    }

}
