package com.library.services;

import com.library.dto.RoleDto;
import com.library.mapper.RoleMapper;
import com.library.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                             .stream()
                             .map(role -> roleMapper.map(role))
                             .collect(Collectors.toList());
    }
}
