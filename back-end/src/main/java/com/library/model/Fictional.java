package com.library.model;

import com.library.model.enums.EnumFictional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fictional")
public class Fictional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private EnumFictional enumFictional;

    @OneToMany(mappedBy = "fictional", cascade = CascadeType.ALL)
    private List<Book> fictionalBookList = new ArrayList<>();

    private String nameOfEnumFictional;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumFictional getEnumFictional() {
        return enumFictional;
    }

    public void setEnumFictional(EnumFictional enumFictional) {
        this.enumFictional = enumFictional;
    }

    public String getNameOfEnumFictional() {
        return nameOfEnumFictional;
    }

    public void setNameOfEnumFictional(String nameOfEnumFictional) {
        this.nameOfEnumFictional = nameOfEnumFictional;
    }

    public List<Book> getFictionalBookList() {
        return fictionalBookList;
    }

    public void setFictionalBookList(List<Book> fictionalBookList) {
        this.fictionalBookList = fictionalBookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fictional fictional = (Fictional) o;
        return id == fictional.id &&
                enumFictional == fictional.enumFictional &&
                Objects.equals(fictionalBookList, fictional.fictionalBookList) &&
                Objects.equals(nameOfEnumFictional, fictional.nameOfEnumFictional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enumFictional, fictionalBookList, nameOfEnumFictional);
    }
}
