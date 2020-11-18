package com.library.model;

import com.library.model.enums.EnumRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRole enumRole;

    private String nameOfRole;

    public Role(EnumRole enumRole) {
        this.enumRole = enumRole;
    }

    public Role() {
    }

}
