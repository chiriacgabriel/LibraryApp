package com.library.model;

import com.library.model.enums.EnumBookCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Book> bookList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumBookCategory getEnumBookCategory() {
        return enumBookCategory;
    }

    public void setEnumBookCategory(EnumBookCategory enumBookCategory) {
        this.enumBookCategory = enumBookCategory;
    }

    public String getNameOfBookCategory() {
        return nameOfBookCategory;
    }

    public void setNameOfBookCategory(String nameOfBookCategory) {
        this.nameOfBookCategory = nameOfBookCategory;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCategory that = (BookCategory) o;
        return id == that.id &&
                enumBookCategory == that.enumBookCategory &&
                Objects.equals(nameOfBookCategory, that.nameOfBookCategory) &&
                Objects.equals(bookList, that.bookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enumBookCategory, nameOfBookCategory, bookList);
    }
}
