package com.library.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stock")
    private int stock;

    @Column(name = "title")
    private String title;

    @ManyToMany
    private Set<Client> clientSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToOne(mappedBy = "book")
    private BookImageUrl bookImageUrl;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Column(name = "book_category")
    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    @Column(name = "type_of_book")
    @Enumerated(EnumType.STRING)
    private TypeOfBook typeOfBook;


    public BookImageUrl getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(BookImageUrl bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public Set<Client> getClientSet() {
        return Collections.unmodifiableSet(clientSet);
    }

    public void setClientSet(Set<Client> clientSet) {
        this.clientSet = new HashSet<>(clientSet);
        clientSet.forEach(client -> client.setBookSet(client.getBookSet()));
    }

    public List<Reservation> getReservationList() {
        return Collections.unmodifiableList(reservationList);
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = new ArrayList<>(reservationList);
        reservationList.forEach(reservation ->  reservation.setBook(this));
    }

    public List<Review> getReviewList() {
        return Collections.unmodifiableList(reviewList);
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = new ArrayList<>(reviewList);
        reviewList.forEach(review -> review.setBook(this));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public TypeOfBook getTypeOfBook() {
        return typeOfBook;
    }

    public void setTypeOfBook(TypeOfBook typeOfBook) {
        this.typeOfBook = typeOfBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                stock == book.stock &&
                Objects.equals(title, book.title) &&
                Objects.equals(clientSet, book.clientSet) &&
                Objects.equals(author, book.author) &&
                Objects.equals(reservationList, book.reservationList) &&
                Objects.equals(bookImageUrl, book.bookImageUrl) &&
                Objects.equals(reviewList, book.reviewList) &&
                bookCategory == book.bookCategory &&
                typeOfBook == book.typeOfBook;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stock, title, clientSet, author, reservationList, bookImageUrl, reviewList, bookCategory, typeOfBook);
    }
}
