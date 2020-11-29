package com.library.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "author_image_url")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author_image")
    private String imageUrl;

}
