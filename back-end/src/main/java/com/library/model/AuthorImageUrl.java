package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "author_image_url")
public class AuthorImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author_image")
    @Lob
    private byte[] imageUrl;

    @OneToMany(mappedBy = "authorImageUrl", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Author> authorSet = new HashSet<>();

}
