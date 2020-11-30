package com.library.mapper;

import com.library.dto.AuthorDto;
import com.library.model.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    public Author map(AuthorDto authorDto) {
        return Author.builder()
                     .firstName(authorDto.getFirstName())
                     .lastName(authorDto.getLastName())
                     .dateOfBirth(authorDto.getDateOfBirth())
                     .nationality(authorDto.getNationality())
                     .description(authorDto.getDescription())
                     .type(authorDto.getType())
                     .bookSet(authorDto.getBookSet())
                     .authorImageUrl(authorDto.getAuthorImageUrl())
                     .build();
    }

    public AuthorDto map(Author author) {

        return AuthorDto.builder()
                        .id(author.getId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .dateOfBirth(author.getDateOfBirth())
                        .nationality(author.getNationality())
                        .description(author.getDescription())
                        .type(author.getType())
                        .bookSet(author.getBookSet())
                        .authorImageUrl(author.getAuthorImageUrl())
                        .build();
    }



}
