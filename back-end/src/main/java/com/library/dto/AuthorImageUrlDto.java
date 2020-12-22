package com.library.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorImageUrlDto {

    private int id;
    private String title;
    private byte[] imageUrl;
}
