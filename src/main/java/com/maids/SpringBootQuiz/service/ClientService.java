package com.maids.SpringBootQuiz.service;

import com.maids.SpringBootQuiz.dto.ClientRequest;
import com.maids.SpringBootQuiz.dto.ClientResponse;
import com.maids.SpringBootQuiz.model.Client;
import com.maids.SpringBootQuiz.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client create(ClientRequest clientRequest) {
        Client client = new Client();
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setMobile(clientRequest.getMobile());
        clientRepository.save(client);
        return client;
    }

    public boolean searchByFullName(String firstName, String lastName) {
        List<Client> clients = clientRepository.findAllByFirstNameAndLastName(firstName, lastName);

        if (clients.isEmpty()) {
            return false;
        }
        return true;

    }

    public boolean searchById(Long id) {
        Optional<Client> clients = clientRepository.findById(id);
        if (clients.isEmpty())
            return false;
        return true;

    }

    public void update(ClientRequest clientRequest, Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client updatedClient = clientOptional.get();
        updatedClient.setFirstName(clientRequest.getFirstName());
        updatedClient.setLastName(clientRequest.getLastName());
        updatedClient.setMobile(clientRequest.getMobile());
        clientRepository.save(updatedClient);
    }

    public List<ClientResponse> fetchAll() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().iterator().forEachRemaining(clients::add);
        List<ClientResponse> clientResponses = new ArrayList<>();
        for (Client client :
             clients) {
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setId(client.getId());
            clientResponse.setFirstName(client.getFirstName());
            clientResponse.setLastName(client.getLastName());
            clientResponse.setMobile(client.getMobile());
            clientResponses.add(clientResponse);
        }
        return clientResponses;
    }
}
