package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.library.model.enums.EnumNonfictional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "nonfictional")
public class Nonfictional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private EnumNonfictional enumNonfictional;

    private String nameOfNonfictional;

    @OneToMany(mappedBy = "nonfictional", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> bookListNonfictional = new ArrayList<>();

}
