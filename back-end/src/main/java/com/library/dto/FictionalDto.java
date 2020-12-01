package com.library.dto;

import com.library.model.Book;
import com.library.model.enums.EnumFictional;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FictionalDto {

    private int id;

    private EnumFictional enumFictional;

    private String nameOfFictional;

    private List<Book> bookListFictional;
}
