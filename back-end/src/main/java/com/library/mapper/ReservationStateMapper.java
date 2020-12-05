package com.library.mapper;

import com.library.dto.ReservationStateDto;
import com.library.model.ReservationState;
import org.springframework.stereotype.Service;

@Service
public class ReservationStateMapper {

    public ReservationState map(ReservationStateDto reservationStateDto) {
        return ReservationState.builder()
                               .enumReservationState(reservationStateDto.getEnumReservationState())
                               .nameOfState(reservationStateDto.getNameOfState())
                               .reservationList(reservationStateDto.getReservationList())
                               .build();
    }

    public ReservationStateDto map(ReservationState reservationState) {
        return ReservationStateDto.builder()
                                  .id(reservationState.getId())
                                  .enumReservationState(reservationState.getEnumReservationState())
                                  .nameOfState(reservationState.getNameOfState())
                                  .reservationList(reservationState.getReservationList())
                                  .build();
    }
}
