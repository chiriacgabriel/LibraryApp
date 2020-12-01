package com.library.dto;

import com.library.model.Book;
import com.library.model.enums.EnumNonfictional;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NonfictionalDto {

    private int id;

    private EnumNonfictional enumNonfictional;

    private String nameOfNonfictional;

    private List<Book> bookListNonfictional;
}
