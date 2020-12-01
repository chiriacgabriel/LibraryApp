package com.library.mapper;

import com.library.dto.RoleDto;
import com.library.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {

    public Role map(RoleDto roleDto){
        return Role.builder()
                .enumRole(roleDto.getEnumRole())
                .nameOfRole(roleDto.getNameOfRole())
                .build();
    }

    public RoleDto map(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .enumRole(role.getEnumRole())
                .nameOfRole(role.getNameOfRole())
                .build();
    }
}
