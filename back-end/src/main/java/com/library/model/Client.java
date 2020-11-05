package com.library.model;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "client_book",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> bookSet = new HashSet<>();

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Book> getBookSet() {
        return Collections.unmodifiableSet(bookSet);
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = new HashSet<>(bookSet);
        bookSet.forEach(book -> book.setClientSet(book.getClientSet()));
    }

    public List<Reservation> getReservationList() {
        return Collections.unmodifiableList(reservationList);
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = new ArrayList<>(reservationList);
        reservationList.forEach(reservation -> reservation.setClient(this));
    }

    public List<Review> getReviewList() {
        return Collections.unmodifiableList(reviewList);
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = new ArrayList<>(reviewList);
        reviewList.forEach(review -> review.setClient(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(name, client.name) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(dateOfBirth, client.dateOfBirth) &&
                Objects.equals(email, client.email) &&
                Objects.equals(reservationList, client.reservationList) &&
                Objects.equals(reviewList, client.reviewList) &&
                Objects.equals(bookSet, client.bookSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, dateOfBirth, email, reservationList, reviewList, bookSet);
    }
}
