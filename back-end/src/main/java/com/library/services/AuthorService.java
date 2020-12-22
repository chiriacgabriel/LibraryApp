package com.library.services;

import com.library.dto.AuthorDto;
import com.library.exception.AuthorNotPresentException;
import com.library.mapper.AuthorMapper;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAllByOrderByFirstNameAsc()
                               .stream()
                               .map(author -> authorMapper.map(author))
                               .collect(Collectors.toList());
    }

    public Optional<AuthorDto> findById(int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.map(author -> authorMapper.map(author));
    }

    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }

    public void addAuthor(AuthorDto authorDto){
        Author author = authorMapper.map(authorDto);
        author.setFirstName(authorDto.getFirstName().trim());
        author.setLastName(authorDto.getLastName().trim());
        authorRepository.save(author);
    }

    public void update(AuthorDto authorDto, int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (!optionalAuthor.isPresent()){
            throw new AuthorNotPresentException();
        }

        Author dbAuthor = optionalAuthor.get();

        dbAuthor.setFirstName(authorDto.getFirstName().trim());
        dbAuthor.setLastName(authorDto.getLastName().trim());
        dbAuthor.setDateOfBirth(authorDto.getDateOfBirth().trim());
        dbAuthor.setNationality(authorDto.getNationality().trim());
        dbAuthor.setDescription(authorDto.getDescription().trim());
        dbAuthor.setType(authorDto.getType().trim());

        dbAuthor.setAuthorImageUrl(authorDto.getAuthorImageUrl());

        authorRepository.save(dbAuthor);
    }

    public Integer countAllAuthors() {
        return authorRepository.countByIdIsNotNull();
    }
}
