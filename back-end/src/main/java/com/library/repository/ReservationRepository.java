package com.library.repository;

import com.library.model.Reservation;
import com.library.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,
        Integer> {

    Page<Reservation> findAllByUser(User user, Pageable pageable);
    List<Reservation> findAllByUser(User user);
}
