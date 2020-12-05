package com.library.model;

import com.library.model.enums.EnumReservationState;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name = "reservation_book",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> bookList = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne
    private Client client;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    private ReservationState reservationState;
}
