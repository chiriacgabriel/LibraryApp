package com.library.dto;

import com.library.model.Reservation;
import com.library.model.enums.EnumReservationState;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationStateDto {

    private int id;
    private EnumReservationState enumReservationState;
    private String nameOfState;
    private List<Reservation> reservationList;
}
