package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.model.enums.EnumReservationState;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "reservation_state")
public class ReservationState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private EnumReservationState enumReservationState;

    private String nameOfState;

    @OneToMany(mappedBy = "reservationState", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservationList;
}
