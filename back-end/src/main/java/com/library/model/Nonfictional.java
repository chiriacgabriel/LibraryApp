package com.library.model;

import com.library.model.enums.EnumNonfictional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

}
