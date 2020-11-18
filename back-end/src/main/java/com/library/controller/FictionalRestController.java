package com.library.controller;

import com.library.model.Fictional;
import com.library.repository.FictionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/fictional", produces = "application/json")
public class FictionalRestController {

    private FictionalRepository fictionalRepository;

    @Autowired
    public FictionalRestController(FictionalRepository fictionalRepository) {
        this.fictionalRepository = fictionalRepository;
    }

    @GetMapping
    public ResponseEntity<List<Fictional>> getAllFictional() {
        List<Fictional> sortedList = fictionalRepository.findAll();
        sortedList.sort(Comparator.comparing(Fictional::getNameOfFictional));
        return ResponseEntity.ok(sortedList);
    }

    @GetMapping("{id}")
    public ResponseEntity<Fictional> getFictionalById(@PathVariable int id) {
        return fictionalRepository.findById(id)
                                  .map(ResponseEntity::ok)
                                  .orElse(ResponseEntity.notFound()
                                                        .build());
    }


}
