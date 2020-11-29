package com.library.validator;

import com.library.dto.AuthorDto;
import com.library.exception.AuthorAlreadyExistsException;
import com.library.exception.AuthorImageException;
import com.library.exception.AuthorMaxDescriptionException;
import com.library.mapper.AuthorMapper;
import com.library.model.Author;
import com.library.payload.response.MessageResponse;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorDtoValidator {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorDtoValidator(AuthorRepository authorRepository,
                              AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public void validate(AuthorDto authorDto) {
        Optional<Author> dbAuthor =
                authorRepository.findByFirstNameAndLastName(authorDto.getFirstName()
                                                                     .trim(),
                        authorDto.getLastName()
                                 .trim());

        if (authorDto.getAuthorImageUrl() == null){
            throw new AuthorImageException();
        }

        if (authorDto.getDescription().trim().length() > 2000){
            throw new AuthorMaxDescriptionException();
        }

        if (dbAuthor.isPresent()) {
            throw new AuthorAlreadyExistsException();
        }
    }

    public void validate(AuthorDto authorDto, int id){
    Optional<Author> optionalAuthor = authorRepository.findById(id);

    Optional<Author> dbAuthorCheckDuplicate =
            authorRepository.findByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName());

    Author dbAuthor = optionalAuthor.get();

    if (!dbAuthor.getFirstName().equals(authorDto.getFirstName()) || !dbAuthor.getLastName().equals(authorDto.getLastName())){
        if (dbAuthorCheckDuplicate.isPresent()){
            throw new AuthorAlreadyExistsException();
        }
        dbAuthor.setFirstName(authorDto.getFirstName());
        dbAuthor.setLastName(authorDto.getLastName());
    }

    }
}
