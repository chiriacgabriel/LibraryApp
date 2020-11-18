package com.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "author", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@authorId")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookSet = new ArrayList<>();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "author_image_url_helper", joinColumns =
    @JoinColumn(name =
            "author_id"),
            inverseJoinColumns = @JoinColumn(name = "author_image_url_id"))
    private AuthorImageUrl authorImageUrl;

}
