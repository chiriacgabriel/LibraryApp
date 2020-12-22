package com.library.services;

import com.library.dto.BookImageUrlDto;
import com.library.mapper.BookImageUrlMapper;
import com.library.model.BookImageUrl;
import com.library.repository.BookImageUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookImageUrlService {

    private BookImageUrlRepository bookImageUrlRepository;
    private BookImageUrlMapper bookImageUrlMapper;

    @Autowired
    public BookImageUrlService(BookImageUrlRepository bookImageUrlRepository, BookImageUrlMapper bookImageUrlMapper) {
        this.bookImageUrlRepository = bookImageUrlRepository;
        this.bookImageUrlMapper = bookImageUrlMapper;
    }

    public List<BookImageUrlDto> getAllBookImages() {
        return bookImageUrlRepository.findAll()
                                     .stream()
                                     .map(bookImageUrl -> bookImageUrlMapper.map(bookImageUrl))
                                     .collect(Collectors.toList());
    }

    public void addBookImageUrl(String title, MultipartFile multipartFile) {

        BookImageUrl bookImageUrl = new BookImageUrl();

        bookImageUrl.setTitle(title.trim());

        try{
            bookImageUrl.setImageUrl(multipartFile.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }

        bookImageUrlRepository.save(bookImageUrl);

    }

    public void deleteBookImageById(int id) {
        bookImageUrlRepository.deleteById(id);
    }
}
