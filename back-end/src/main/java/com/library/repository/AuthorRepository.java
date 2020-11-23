package com.library.repository;

import com.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("select a from Author a where a.name =:name and a.lastName " +
            "=:lastName")
    Optional<Author> findByNameAndLastName(@Param("name") String name,
                                           @Param("lastName") String lastName);

}
