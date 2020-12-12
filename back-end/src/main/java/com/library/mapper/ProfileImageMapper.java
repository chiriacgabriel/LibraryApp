package com.library.mapper;

import com.library.dto.ProfileImageDto;
import com.library.model.ProfileImage;
import org.springframework.stereotype.Service;

@Service
public class ProfileImageMapper {
    public ProfileImage map(ProfileImageDto profileImageDto){
        return ProfileImage.builder()
                .name(profileImageDto.getName())
                .type(profileImageDto.getType())
                .image(profileImageDto.getImage())
                .user(profileImageDto.getUser())
                .build();
    }

    public ProfileImageDto map(ProfileImage profileImage){
        return ProfileImageDto.builder()
                .id(profileImage.getId())
                .name(profileImage.getName())
                .type(profileImage.getType())
                .image(profileImage.getImage())
                .user(profileImage.getUser())
                .build();
    }
}
