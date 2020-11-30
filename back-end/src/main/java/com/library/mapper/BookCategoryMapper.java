package com.library.mapper;

import com.library.dto.BookCategoryDto;
import com.library.model.BookCategory;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryMapper {

    public BookCategory map(BookCategoryDto bookCategoryDto){
        return BookCategory.builder()
                           .enumBookCategory(bookCategoryDto.getEnumBookCategory())
                           .nameOfBookCategory(bookCategoryDto.getNameOfBookCategory())
                           .bookListCategory(bookCategoryDto.getBookListCategory())
                           .build();
    }

    public BookCategoryDto map(BookCategory bookCategory){
        return BookCategoryDto.builder()
                              .id(bookCategory.getId())
                              .enumBookCategory(bookCategory.getEnumBookCategory())
                              .nameOfBookCategory(bookCategory.getNameOfBookCategory())
                              .bookListCategory(bookCategory.getBookListCategory())
                              .build();
    }

}
