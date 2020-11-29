package com.library.dto;

import com.library.model.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private int id;

    private String title;

    private Author author;

    private int stock;

    private BookImageUrl bookImageUrl;

    private BookCategory bookCategory;

    private Fictional fictional;

    private Nonfictional nonfictional;

}
