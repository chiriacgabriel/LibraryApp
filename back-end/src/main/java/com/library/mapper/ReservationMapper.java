package com.library.mapper;

import com.library.dto.ReservationDto;
import com.library.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReservationMapper {

    public Reservation map(ReservationDto reservationDto) {
        return Reservation.builder()
                .bookList(reservationDto.getBookList())
                .user(reservationDto.getUser())
                .client(reservationDto.getClient())
                .startDate(reservationDto.getStartDate())
                .endDate(reservationDto.getEndDate())
                .reservationState(reservationDto.getReservationState())
                .processedDate(reservationDto.getProcessedDate())
                .build();
    }

    public ReservationDto map(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .bookList(reservation.getBookList())
                .user(reservation.getUser())
                .client(reservation.getClient())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .reservationState(reservation.getReservationState())
                .processedDate(reservation.getProcessedDate())
                .build();
    }

}
