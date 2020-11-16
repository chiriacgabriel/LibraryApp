package com.library.repository;

import com.library.model.Nonfictional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonfictionalRepository extends JpaRepository<Nonfictional,
        Integer> {
}
