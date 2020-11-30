package com.library.services;

import com.library.dto.BookCategoryDto;
import com.library.mapper.BookCategoryMapper;
import com.library.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCategoryService {
    private BookCategoryRepository bookCategoryRepository;
    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository, BookCategoryMapper bookCategoryMapper) {
        this.bookCategoryRepository = bookCategoryRepository;
        this.bookCategoryMapper = bookCategoryMapper;
    }

    public List<BookCategoryDto> getAllBookCategories() {
        return bookCategoryRepository.findAll()
                                     .stream()
                                     .map(bookCategory -> bookCategoryMapper.map(bookCategory))
                                     .collect(Collectors.toList());
    }
}
