package com.library.services;


import com.library.dto.ProfileImageDto;
import com.library.mapper.ProfileImageMapper;
import com.library.model.ProfileImage;
import com.library.model.User;
import com.library.repository.ProfileImageRepository;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void store(MultipartFile multipartFile, int id) throws IOException {

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("No User Found");
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        ProfileImage profileImage = new ProfileImage();

        profileImage.setName(fileName);
        profileImage.setType(multipartFile.getContentType());
        profileImage.setImage(multipartFile.getBytes());
        profileImage.setUser(optionalUser.get());

        profileImageRepository.save(profileImage);
    }

    public Optional<ProfileImageDto> getImageByUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()){
            throw new IllegalArgumentException("User not found");
        }

        Optional<ProfileImage> profileImageOption = profileImageRepository.findAllByUser(optionalUser.get());

        return profileImageOption.map(profileImage -> profileImageMapper.map(profileImage));
    }
}
