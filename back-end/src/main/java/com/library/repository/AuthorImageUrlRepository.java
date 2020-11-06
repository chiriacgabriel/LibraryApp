package com.library.repository;

import com.library.model.AuthorImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorImageUrlRepository extends JpaRepository<AuthorImageUrl, Integer> {

}
