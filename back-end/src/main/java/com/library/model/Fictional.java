package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.model.enums.EnumFictional;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "fictional")
public class Fictional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private EnumFictional enumFictional;

    private String nameOfFictional;

    @OneToMany(mappedBy = "fictional", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> bookListFictional = new ArrayList<>();
}
