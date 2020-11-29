package com.library.mapper;

import com.library.dto.AuthorImageUrlDto;
import com.library.model.AuthorImageUrl;
import org.springframework.stereotype.Service;

@Service
public class AuthorImageUrlMapper {

    public AuthorImageUrl map(AuthorImageUrlDto authorImageUrlDto) {

        AuthorImageUrl authorImageUrl = AuthorImageUrl.builder()
                                                      .title(authorImageUrlDto.getTitle())
                                                      .imageUrl(authorImageUrlDto.getImageUrl())
                                                      .build();
        return authorImageUrl;
    }

    public AuthorImageUrlDto map(AuthorImageUrl authorImageUrl) {

        AuthorImageUrlDto authorImageUrlDto = AuthorImageUrlDto.builder()
                                                               .id(authorImageUrl.getId())
                                                               .title(authorImageUrl.getTitle())
                                                               .imageUrl(authorImageUrl.getImageUrl())
                                                               .build();
        return authorImageUrlDto;
    }
}
