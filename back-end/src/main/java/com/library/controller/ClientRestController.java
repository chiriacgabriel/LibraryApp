package com.library.controller;

import com.library.dto.ClientDto;
import com.library.services.ClientService;
import com.library.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/clients", produces = "application/json")
public class ClientRestController {

    private ClientService clientService;
    private ClientValidator clientValidator;

    @Autowired
    public ClientRestController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable int id) {
        return clientService.findClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDto> deleteClientById(@PathVariable int id) {
        clientService.deleteClientId(id);
        return ResponseEntity.ok().build();
    }

    //Create
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        clientService.addClient(clientDto);
        return ResponseEntity.ok().build();
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClientById(@PathVariable int id,
                                              @RequestBody ClientDto clientDto) {

        clientService.update(id, clientDto);

        return ResponseEntity.ok().build();
    }

}
