package com.library.services;

import com.library.dto.ClientDto;
import com.library.exception.ClientMissingException;
import com.library.mapper.ClientMapper;
import com.library.model.Client;
import com.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDto> getAllClients() {
      return clientRepository.findAllByOrderByFirstNameAsc()
                        .stream()
                        .map(client -> clientMapper.map(client))
                        .collect(Collectors.toList());
    }

    public Optional<ClientDto> findClientById(int id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(client -> clientMapper.map(client));
    }

    public void deleteClientId(int id) {
        clientRepository.deleteById(id);
    }

    public void addClient(ClientDto clientDto) {
        Client client = clientMapper.map(clientDto);
        clientRepository.save(client);
    }

    public void update(int id, ClientDto clientDto) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (!optionalClient.isPresent()){
            throw new ClientMissingException();
        }

        Client dbClient = optionalClient.get();

        dbClient.setFirstName(clientDto.getFirstName());
        dbClient.setLastName(clientDto.getLastName());
        dbClient.setEmail(clientDto.getEmail());
        dbClient.setPhoneNumber(clientDto.getPhoneNumber());

        clientRepository.save(dbClient);
    }
}
