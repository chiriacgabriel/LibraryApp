package com.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "type")
    private String type;

    @JsonIgnoreProperties("author")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookSet = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "author_image_url_id")
    private AuthorImageUrl authorImageUrl;

}
