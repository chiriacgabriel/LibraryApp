package com.library.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private int id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}
