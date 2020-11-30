package com.library.validator;

import com.library.dto.ClientDto;
import com.library.model.Client;
import com.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientValidator {

    private ClientRepository clientRepository;

    @Autowired
    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

}
