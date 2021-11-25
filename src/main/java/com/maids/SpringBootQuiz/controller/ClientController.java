package com.maids.SpringBootQuiz.controller;


import com.maids.SpringBootQuiz.dto.ClientRequest;

import com.maids.SpringBootQuiz.dto.ClientResponse;
import com.maids.SpringBootQuiz.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/client")
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ClientRequest clientRequest) {

        if (clientService.searchByFullName(clientRequest.getFirstName(), clientRequest.getLastName()) ){
            return ResponseEntity.badRequest().body("Client already exists");
        } else {

            clientService.create(clientRequest);
            return ResponseEntity.ok("Client has been created successfully!");
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
        public ResponseEntity<?> update(@RequestBody ClientRequest clientRequest, @PathVariable Long id){
        if (clientService.searchById(id) ){
            clientService.update(clientRequest,id);
            return ResponseEntity.ok("Client has been updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Client doesn't exist");
        }

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ClientResponse> fetchAll() {
       return clientService.fetchAll();

    }


}




