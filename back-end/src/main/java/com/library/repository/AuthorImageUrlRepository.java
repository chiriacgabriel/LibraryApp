package com.library.repository;

import com.library.model.AuthorImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorImageUrlRepository extends JpaRepository<AuthorImageUrl, Integer> {

    Boolean existsByTitle(String title);

    List<AuthorImageUrl> findAllByOrderByTitleAsc();

}
