package com.library.controller;


import com.library.dto.ProfileImageDto;
import com.library.services.ProfileImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/profile-image", produces = "application/json")
public class ProfileImageRestController {

    private ProfileImageService profileImageService;

    @Autowired
    public ProfileImageRestController(ProfileImageService profileImageService) {
        this.profileImageService = profileImageService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProfileImageDto> uploadProfileImage(@PathVariable int id,
                                                              @RequestParam("file") MultipartFile multipartFile) {
        try {
            profileImageService.store(multipartFile, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileImageDto> getAllImagesProfile(@PathVariable int id) {
        return profileImageService.getImageByUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
