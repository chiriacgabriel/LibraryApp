package com.library.dto;

import com.library.model.AuthorImageUrl;
import com.library.model.Book;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private int id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationality;
    private String description;
    private String type;
    private List<Book> bookSet;
    private AuthorImageUrl authorImageUrl;
}
