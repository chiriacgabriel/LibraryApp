package com.library.controller;

import com.library.dto.NonfictionalDto;
import com.library.model.Nonfictional;
import com.library.repository.NonfictionalRepository;
import com.library.services.NonfictionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/nonfictional", produces = "application/json")
public class NonfictionalRestController {

    private NonfictionalService nonfictionalService;

    @Autowired
    public NonfictionalRestController(NonfictionalService nonfictionalService) {
        this.nonfictionalService = nonfictionalService;
    }

    @GetMapping
    public ResponseEntity<List<NonfictionalDto>> getAllNonfictional() {
        return ResponseEntity.ok(nonfictionalService.getAllNonfictional());
    }

}
