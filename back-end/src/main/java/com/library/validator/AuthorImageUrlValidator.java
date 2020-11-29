package com.library.validator;

import com.library.dto.AuthorImageUrlDto;
import com.library.exception.AuthorImageException;
import com.library.repository.AuthorImageUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorImageUrlValidator {

    private AuthorImageUrlRepository authorImageUrlRepository;

    @Autowired
    public AuthorImageUrlValidator(AuthorImageUrlRepository authorImageUrlRepository) {
        this.authorImageUrlRepository = authorImageUrlRepository;
    }

    public void validate(AuthorImageUrlDto authorImageUrlDto) {
        if (authorImageUrlRepository.existsByTitle(authorImageUrlDto.getTitle()
                                                                    .trim())) {
            throw new AuthorImageException();
        }
    }
}
