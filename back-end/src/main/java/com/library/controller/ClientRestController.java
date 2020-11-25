package com.library.controller;

import com.library.model.Client;
import com.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/clients", produces = "application/json")
public class ClientRestController {

    private ClientRepository clientRepository;

    @Autowired
    public ClientRestController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clientList = clientRepository.findAll();

        clientList.sort(Comparator.comparing(Client::getName));

        return ResponseEntity.ok(clientList);
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        return clientRepository.findById(id)
                               .map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound()
                                                     .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClientById(@PathVariable int id){
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Create
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return ResponseEntity.ok(clientRepository.save(client));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClientById(@PathVariable int id,
                                                   @RequestBody Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (!optionalClient.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Client dbClient = optionalClient.get();

        dbClient.setName(client.getName());
        dbClient.setLastName(client.getLastName());
        dbClient.setEmail(client.getEmail());
        dbClient.setPhoneNumber(client.getPhoneNumber());

        return ResponseEntity.ok(clientRepository.save(dbClient));
    }

}
