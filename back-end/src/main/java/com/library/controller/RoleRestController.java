package com.library.controller;

import com.library.model.Role;
import com.library.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/users/roles", produces = "application/json")
public class RoleRestController {
    private RoleRepository roleRepository;

    @Autowired
    public RoleRestController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
