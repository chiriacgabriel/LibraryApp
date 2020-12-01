package com.library.repository;

import com.library.model.Fictional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FictionalRepository extends JpaRepository<Fictional, Integer> {

    List<Fictional> findAllByOrderByNameOfFictionalAsc();
}
