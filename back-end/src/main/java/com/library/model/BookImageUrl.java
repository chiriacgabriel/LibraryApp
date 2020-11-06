package com.library.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book_image_url")
public class BookImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookImageUrl that = (BookImageUrl) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imageUrl, book);
    }
}
