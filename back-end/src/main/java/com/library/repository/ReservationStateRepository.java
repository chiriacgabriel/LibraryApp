package com.library.repository;

import com.library.model.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationStateRepository extends JpaRepository<ReservationState, Integer> {
}
