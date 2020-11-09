package com.library.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "author_image_url")
public class AuthorImageUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author_image")
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String authorImage) {
        this.imageUrl = authorImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorImageUrl authorImageUrl = (AuthorImageUrl) o;
        return id == authorImageUrl.id &&
                Objects.equals(imageUrl, authorImageUrl.imageUrl) &&
                Objects.equals(title, authorImageUrl.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, title);
    }
}
