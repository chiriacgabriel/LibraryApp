package com.library.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile_Image")
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String type;

    @Lob
    private byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "profile_image_helper",
            joinColumns = @JoinColumn(name = "profile_image_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;


}
