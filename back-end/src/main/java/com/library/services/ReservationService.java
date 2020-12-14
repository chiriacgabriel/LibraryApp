package com.library.services;

import com.library.dto.ReservationDto;
import com.library.exception.ReservationNotPresentException;
import com.library.mapper.ReservationMapper;
import com.library.model.Reservation;
import com.library.model.User;
import com.library.repository.ReservationRepository;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationMapper reservationMapper;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationMapper reservationMapper,
                              ReservationRepository reservationRepository,
                              UserRepository userRepository) {
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
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

        if (!optionalReservation.isPresent()) {
            throw new ReservationNotPresentException();
        }

        Reservation dbReservation = optionalReservation.get();

        dbReservation.setBookList(reservationDto.getBookList());
        dbReservation.setUser(reservationDto.getUser());
        dbReservation.setClient(reservationDto.getClient());
        dbReservation.setStartDate(reservationDto.getStartDate());
        dbReservation.setEndDate(reservationDto.getEndDate());
        dbReservation.setReservationState(reservationDto.getReservationState());

        reservationRepository.save(dbReservation);
    }


    public Page<ReservationDto> getAllReservationsByUser(int id, int page, int size) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User email is not found");
        }

        Pageable pageable = PageRequest.of(page, size);

        List<ReservationDto> reservationDtoList = reservationRepository.findAllByUser(optionalUser.get(), pageable)
                                                                        .stream()
                                                                        .map(reservation -> reservationMapper.map(reservation))
                                                                        .collect(Collectors.toList());

        return new PageImpl<>(reservationDtoList,
                                        pageable,
                                        reservationRepository.findAllByUser(optionalUser.get()).size());
    }
}
