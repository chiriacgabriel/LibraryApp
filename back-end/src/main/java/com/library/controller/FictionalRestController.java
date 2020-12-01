package com.library.controller;

import com.library.dto.FictionalDto;
import com.library.services.FictionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/fictional", produces = "application/json")
public class FictionalRestController {

    private FictionalService fictionalService;

    @Autowired
    public FictionalRestController(FictionalService fictionalService) {
        this.fictionalService = fictionalService;
    }

    @GetMapping
    public ResponseEntity<List<FictionalDto>> getAllFictionals() {
        return ResponseEntity.ok(fictionalService.getAllFictionals());
    }


}
