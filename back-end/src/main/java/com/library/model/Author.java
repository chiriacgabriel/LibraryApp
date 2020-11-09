package com.library.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "author_image_url_helper" , joinColumns =
    @JoinColumn(name =
            "author_id"),
    inverseJoinColumns = @JoinColumn(name = "author_image_url_id"))
    private AuthorImageUrl authorImageUrl;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Book> bookList = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorImageUrl getAuthorImageUrl() {
        return authorImageUrl;
    }

    public void setAuthorImageUrl(AuthorImageUrl authorImageUrl) {
        this.authorImageUrl = authorImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Book> getBookList() {
        return Collections.unmodifiableSet(bookList);
    }

    public void setBookList(Collection<Book> bookList) {
        this.bookList = new HashSet<>(bookList);
        bookList.forEach(book -> book.setAuthor(this));
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(name, author.name) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(dateOfBirth, author.dateOfBirth) &&
                Objects.equals(bookList, author.bookList) &&
                Objects.equals(authorImageUrl, author.authorImageUrl) &&
                Objects.equals(description, author.description) &&
                Objects.equals(nationality, author.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, dateOfBirth, bookList,
                authorImageUrl, description, nationality);
    }
}
