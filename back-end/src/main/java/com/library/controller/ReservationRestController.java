package com.library.controller;

import com.library.dto.ReservationDto;
import com.library.services.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/reservations", produces = "application/json")
public class ReservationRestController {

    private ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Page<ReservationDto>> getAllReservationsByUser(@PathVariable int userId,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "8") int size) {
        return ResponseEntity.ok(reservationService.getAllReservationsByUser(userId, page, size));
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable int id) {
        return reservationService.findReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDto> deleteReservationById(@PathVariable int id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.ok()
                .build();
    }

    //Create
    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.addReservation(reservationDto);
        return ResponseEntity.ok().build();
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservationById(@PathVariable int id,
                                                   @RequestBody ReservationDto reservationDto) {

        reservationService.updateReservation(id, reservationDto);

        return ResponseEntity.ok().build();
    }
}
