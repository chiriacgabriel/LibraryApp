package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.model.enums.EnumBookCategory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book_category")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private EnumBookCategory enumBookCategory;

    private String nameOfBookCategory;

    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> bookListCategory = new ArrayList<>();

}
