package com.library.dto;

import com.library.model.Book;
import com.library.model.Client;
import com.library.model.ReservationState;
import com.library.model.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private int id;

    private List<Book> bookList;

    private User user;

    private Client client;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime processedDate;

    private ReservationState reservationState;

}
