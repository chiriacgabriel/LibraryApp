package com.library.mapper;

import com.library.dto.ClientDto;
import com.library.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public Client map(ClientDto clientDto) {
        return Client.builder()
                     .firstName(clientDto.getFirstName())
                     .lastName(clientDto.getLastName())
                     .phoneNumber(clientDto.getPhoneNumber())
                     .email(clientDto.getEmail())
                     .reservationList(clientDto.getReservationList())
                     .build();
    }

    public ClientDto map(Client client) {
        return ClientDto.builder()
                        .id(client.getId())
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .phoneNumber(client.getPhoneNumber())
                        .email(client.getEmail())
                        .reservationList(client.getReservationList())
                        .build();
    }
}
