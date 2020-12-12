package com.library.dto;

import com.library.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImageDto {

    private int id;
    private String name;
    private String type;
    private byte[] image;
    private User user;
}
