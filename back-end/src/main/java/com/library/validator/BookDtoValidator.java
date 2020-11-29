package com.library.validator;

import com.library.dto.BookDto;
import com.library.exception.BookNoAuthorException;
import com.library.exception.BookNoCategoryException;
import com.library.exception.BookNoTypeSelectedException;
import org.springframework.stereotype.Service;

@Service
public class BookDtoValidator {

    public void validate(BookDto bookDto) {
        if (bookDto.getAuthor() == null){
            throw new BookNoAuthorException();
        }
        if (bookDto.getBookCategory() == null){
            throw new BookNoCategoryException();
        }
        if (bookDto.getFictional() == null && bookDto.getNonfictional() == null){
            throw new BookNoTypeSelectedException();
        }
    }
}
