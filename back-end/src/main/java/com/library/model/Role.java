package com.library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public String getNameOfRole() {
        return getEnumRole().getNameRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumRole getEnumRole() {
        return enumRole;
    }

    public void setEnumRole(EnumRole enumRole) {
        this.enumRole = enumRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                enumRole == role.enumRole &&
                Objects.equals(nameOfRole, role.nameOfRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enumRole, nameOfRole);
    }
}
