package com.library.repository;

import com.library.model.BookImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageUrlRepository extends JpaRepository<BookImageUrl,
        Integer> {

    Boolean existsByTitle(String title);

}
