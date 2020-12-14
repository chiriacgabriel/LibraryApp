package com.library.services;


import com.library.dto.ProfileImageDto;
import com.library.mapper.ProfileImageMapper;
import com.library.model.ProfileImage;
import com.library.model.User;
import com.library.repository.ProfileImageRepository;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProfileImageService {

    private ProfileImageRepository profileImageRepository;
    private ProfileImageMapper profileImageMapper;
    private UserRepository userRepository;

    @Autowired
    public ProfileImageService(ProfileImageRepository profileImageRepository, ProfileImageMapper profileImageMapper, UserRepository userRepository) {
        this.profileImageRepository = profileImageRepository;
        this.profileImageMapper = profileImageMapper;
        this.userRepository = userRepository;
    }

    public Optional<ProfileImageDto> getImageByUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }

        Optional<ProfileImage> profileImageOption = profileImageRepository.findAllByUser(optionalUser.get());

        return profileImageOption.map(profileImage -> profileImageMapper.map(profileImage));
    }

    public void update(MultipartFile multipartFile, int id) {
        Optional<ProfileImage> optionalProfileImage = profileImageRepository.findById(id);

        if (!optionalProfileImage.isPresent()) {
            throw new IllegalArgumentException("Profile image not found");
        }

        ProfileImage dbProfileImage = optionalProfileImage.get();

        dbProfileImage.setName(multipartFile.getName());
        dbProfileImage.setType(multipartFile.getContentType());
        try {
            dbProfileImage.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        profileImageRepository.save(dbProfileImage);

    }
}
