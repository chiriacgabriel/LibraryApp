package com.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.library.model.enums.EnumFictional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Author author;

    @Column(name = "stock")
    private int stock;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "book_image_url_helper" , joinColumns =
    @JoinColumn(name =
            "book_id"),
            inverseJoinColumns = @JoinColumn(name = "book_image_url_id"))
    private BookImageUrl bookImageUrl;

}
