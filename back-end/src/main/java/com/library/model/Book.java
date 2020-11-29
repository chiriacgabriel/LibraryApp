package com.library.model;

import com.fasterxml.jackson.annotation.*;
import com.library.model.enums.EnumFictional;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "stock")
    private int stock;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "book_image_url_helper" , joinColumns =
    @JoinColumn(name =
            "book_id"),
            inverseJoinColumns = @JoinColumn(name = "book_image_url_id"))
    private BookImageUrl bookImageUrl;

    @ManyToOne
    @JoinColumn(name = "book_category_id")
    private BookCategory bookCategory;

    @ManyToOne
    @JoinColumn(name = "fictional_id")
    private Fictional fictional;

    @ManyToOne
    @JoinColumn(name = "nonfictional_id")
    private Nonfictional nonfictional;
}
