package com.library.dto;

import com.library.model.Book;
import com.library.model.enums.EnumBookCategory;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryDto {

    private int id;

    private EnumBookCategory enumBookCategory;

    private String nameOfBookCategory;

    private List<Book> bookListCategory;
}
