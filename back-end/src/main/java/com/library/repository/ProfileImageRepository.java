package com.library.repository;

import com.library.model.ProfileImage;
import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer> {

    Optional<ProfileImage> findAllByUser (User user);
}
