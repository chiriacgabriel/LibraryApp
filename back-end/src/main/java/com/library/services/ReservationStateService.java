package com.library.services;

import com.library.dto.ReservationStateDto;
import com.library.mapper.ReservationStateMapper;
import com.library.repository.ReservationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationStateService {

    private ReservationStateRepository reservationStateRepository;
    private ReservationStateMapper reservationStateMapper;

    @Autowired
    public ReservationStateService(ReservationStateRepository reservationStateRepository, ReservationStateMapper reservationStateMapper) {
        this.reservationStateRepository = reservationStateRepository;
        this.reservationStateMapper = reservationStateMapper;
    }


    public List<ReservationStateDto> getAllStates() {
        return reservationStateRepository.findAll()
                                         .stream()
                                         .map(reservationState -> reservationStateMapper.map(reservationState))
                                         .collect(Collectors.toList());
    }
}
