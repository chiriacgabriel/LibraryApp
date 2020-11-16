package com.library.repository;

import com.library.model.Fictional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FictionalRepository extends JpaRepository<Fictional, Integer> {
}
