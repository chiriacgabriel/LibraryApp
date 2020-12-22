package com.library.validator;

import com.library.dto.BookImageUrlDto;
import com.library.exception.BookImageException;
import com.library.repository.BookImageUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookImageUrlValidator {

    private BookImageUrlRepository bookImageUrlRepository;

    @Autowired
    public BookImageUrlValidator(BookImageUrlRepository bookImageUrlRepository) {
        this.bookImageUrlRepository = bookImageUrlRepository;
    }

    public void validate(String title) {
        if (bookImageUrlRepository.existsByTitle(title.trim())){
            throw new BookImageException();
        }
    }
}
