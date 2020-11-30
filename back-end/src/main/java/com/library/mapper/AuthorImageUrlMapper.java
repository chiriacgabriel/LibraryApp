package com.library.mapper;

import com.library.dto.AuthorImageUrlDto;
import com.library.model.AuthorImageUrl;
import org.springframework.stereotype.Service;

@Service
public class AuthorImageUrlMapper {

    public AuthorImageUrl map(AuthorImageUrlDto authorImageUrlDto) {

        return AuthorImageUrl.builder()
                             .title(authorImageUrlDto.getTitle())
                             .imageUrl(authorImageUrlDto.getImageUrl())
                             .build();
    }

    public AuthorImageUrlDto map(AuthorImageUrl authorImageUrl) {

        return AuthorImageUrlDto.builder()
                                .id(authorImageUrl.getId())
                                .title(authorImageUrl.getTitle())
                                .imageUrl(authorImageUrl.getImageUrl())
                                .build();
    }
}
