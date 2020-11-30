package com.library.services;

import com.library.dto.AuthorImageUrlDto;
import com.library.mapper.AuthorImageUrlMapper;
import com.library.model.AuthorImageUrl;
import com.library.repository.AuthorImageUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorImageUrlService {

    private AuthorImageUrlRepository authorImageUrlRepository;
    private AuthorImageUrlMapper authorImageUrlMapper;

    @Autowired
    public AuthorImageUrlService(AuthorImageUrlRepository authorImageUrlRepository, AuthorImageUrlMapper authorImageUrlMapper) {
        this.authorImageUrlRepository = authorImageUrlRepository;
        this.authorImageUrlMapper = authorImageUrlMapper;
    }

    public List<AuthorImageUrlDto> getAllImages() {
        return authorImageUrlRepository.findAllByOrderByTitleAsc()
                                       .stream()
                                       .map(authorImageUrl -> authorImageUrlMapper.map(authorImageUrl))
                                       .collect(Collectors.toList());
    }

    public void addImageUrl(AuthorImageUrlDto authorImageUrlDto) {
        AuthorImageUrl authorImageUrl =
                authorImageUrlMapper.map(authorImageUrlDto);
        authorImageUrl.setTitle(authorImageUrlDto.getTitle().trim());
        authorImageUrlRepository.save(authorImageUrl);
    }

    public void deleteById(int id) {
        authorImageUrlRepository.deleteById(id);
    }
}
