package com.library.mapper;

import com.library.dto.ReservationDto;
import com.library.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {

    public Reservation map(ReservationDto reservationDto){
        return Reservation.builder()
                .bookList(reservationDto.getBookList())
                .userList(reservationDto.getUserList())
                .clientList(reservationDto.getClientList())
                .startDate(reservationDto.getStartDate())
                .endDate(reservationDto.getEndDate())
                .build();
    }

    public ReservationDto map(Reservation reservation){
        return ReservationDto.builder()
                .id(reservation.getId())
                .bookList(reservation.getBookList())
                .userList(reservation.getUserList())
                .clientList(reservation.getClientList())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }
}
