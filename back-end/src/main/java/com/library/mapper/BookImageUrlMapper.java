package com.library.mapper;

import com.library.dto.BookImageUrlDto;
import com.library.model.BookImageUrl;
import org.springframework.stereotype.Service;

@Service
public class BookImageUrlMapper {

    public BookImageUrl map(BookImageUrlDto bookImageUrlDto){
        return BookImageUrl.builder()
                .title(bookImageUrlDto.getTitle())
                .imageUrl(bookImageUrlDto.getImageUrl())
                .build();
    }

    public BookImageUrlDto map(BookImageUrl bookImageUrl){
        return BookImageUrlDto.builder()
                .id(bookImageUrl.getId())
                .title(bookImageUrl.getTitle())
                .imageUrl(bookImageUrl.getImageUrl())
                .build();
    }

}
