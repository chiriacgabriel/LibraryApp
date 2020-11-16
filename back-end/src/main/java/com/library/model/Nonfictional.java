package com.library.model;

import com.library.model.enums.EnumNonfictional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Book> nonfictionalBookList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumNonfictional getEnumNonfictional() {
        return enumNonfictional;
    }

    public void setEnumNonfictional(EnumNonfictional enumNonfictional) {
        this.enumNonfictional = enumNonfictional;
    }

    public String getNameOfNonfictional() {
        return nameOfNonfictional;
    }

    public void setNameOfNonfictional(String nameOfNonfictional) {
        this.nameOfNonfictional = nameOfNonfictional;
    }

    public List<Book> getNonfictionalBookList() {
        return nonfictionalBookList;
    }

    public void setNonfictionalBookList(List<Book> nonfictionalBookList) {
        this.nonfictionalBookList = nonfictionalBookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nonfictional that = (Nonfictional) o;
        return id == that.id &&
                enumNonfictional == that.enumNonfictional &&
                Objects.equals(nameOfNonfictional, that.nameOfNonfictional) &&
                Objects.equals(nonfictionalBookList, that.nonfictionalBookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enumNonfictional, nameOfNonfictional, nonfictionalBookList);
    }
}
