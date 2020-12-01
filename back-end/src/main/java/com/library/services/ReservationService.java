package com.library.services;

import com.library.dto.ReservationDto;
import com.library.exception.ReservationNotPresentException;
import com.library.mapper.ReservationMapper;
import com.library.model.Reservation;
import com.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationMapper reservationMapper;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationMapper reservationMapper, ReservationRepository reservationRepository) {
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> reservationMapper.map(reservation))
                .collect(Collectors.toList());
    }

    public Optional<ReservationDto> findReservationById(int id) {
        Optional<Reservation> optionalReservation =
                reservationRepository.findById(id);
        return optionalReservation.map(reservation -> reservationMapper.map(reservation));
    }

    public void deleteReservationById(int id) {
        reservationRepository.deleteById(id);
    }

    public void addReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.map(reservationDto);
        reservationRepository.save(reservation);
    }

    public void updateReservation(int id, ReservationDto reservationDto) {
        Optional<Reservation> optionalReservation =
                reservationRepository.findById(id);

        if (!optionalReservation.isPresent()){
            throw new ReservationNotPresentException();
        }

        Reservation dbReservation = optionalReservation.get();

        dbReservation.setBookList(reservationDto.getBookList());
        dbReservation.setUserList(reservationDto.getUserList());
        dbReservation.setClientList(reservationDto.getClientList());
        dbReservation.setStartDate(reservationDto.getStartDate());
        dbReservation.setEndDate(reservationDto.getEndDate());

        reservationRepository.save(dbReservation);
    }
}
