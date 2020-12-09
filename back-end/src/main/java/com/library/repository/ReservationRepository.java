package com.library.repository;

import com.library.model.Reservation;
import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,
        Integer> {

    List<Reservation> findAllByUser(User user);
}
