package com.library.controller;

import com.library.model.Nonfictional;
import com.library.repository.NonfictionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/nonfictional", produces = "application/json")
public class NonfictionalRestController {
    private NonfictionalRepository nonfictionalRepository;

    @Autowired
    public NonfictionalRestController(NonfictionalRepository nonfictionalRepository) {
        this.nonfictionalRepository = nonfictionalRepository;
    }

    @GetMapping
    public ResponseEntity<List<Nonfictional>> getAllNonfictional() {
        List<Nonfictional> sortedNonfictional =
                nonfictionalRepository.findAll();
        sortedNonfictional.sort(Comparator.comparing(Nonfictional::getNameOfNonfictional));
        return ResponseEntity.ok(sortedNonfictional);
    }

    @GetMapping("{id}")
    public ResponseEntity<Nonfictional> getNonfictionalById(@PathVariable int id) {
        return nonfictionalRepository.findById(id)
                                     .map(ResponseEntity::ok)
                                     .orElse(ResponseEntity.notFound()
                                                           .build());
    }
}
