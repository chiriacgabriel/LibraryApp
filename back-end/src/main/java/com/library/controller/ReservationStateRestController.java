package com.library.controller;

import com.library.dto.ReservationStateDto;
import com.library.services.ReservationStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/state", produces = "application/json")
public class ReservationStateRestController {

    private ReservationStateService reservationStateService;

    @Autowired
    public ReservationStateRestController(ReservationStateService reservationStateService) {
        this.reservationStateService = reservationStateService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationStateDto>> getAllStates(){
        return ResponseEntity.ok(reservationStateService.getAllStates());
    }
}
