package com.library.dto;

import com.library.model.enums.EnumRole;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private int id;

    private EnumRole enumRole;

    private String nameOfRole;

}
