package com.library.controller;

import com.library.model.Reservation;
import com.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/reservations", produces = "application/json")
public class ReservationRestController {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationRestController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationRepository.findAll());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        return reservationRepository.findById(id)
                                    .map(ResponseEntity::ok)
                                    .orElse(ResponseEntity.notFound()
                                                          .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservationById(@PathVariable int id) {
        reservationRepository.deleteById(id);
        return ResponseEntity.ok()
                             .build();
    }

    //Create
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservationById(@PathVariable int id,
                                                             @RequestBody Reservation reservation){
        Optional<Reservation> optionalReservation =
                reservationRepository.findById(id);

        if (!optionalReservation.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Reservation dbReservation = optionalReservation.get();

        dbReservation.setBookList(reservation.getBookList());
        dbReservation.setUserList(reservation.getUserList());
        dbReservation.setClientList(reservation.getClientList());
        dbReservation.setStartDate(reservation.getStartDate());
        dbReservation.setEndDate(reservation.getEndDate());

        return ResponseEntity.ok(reservationRepository.save(dbReservation));
    }
}
