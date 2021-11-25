package com.maids.SpringBootQuiz.controller;


import com.maids.SpringBootQuiz.dto.SalesRequest;
import com.maids.SpringBootQuiz.dto.SalesResponse;
import com.maids.SpringBootQuiz.dto.SoldProductRequest;
import com.maids.SpringBootQuiz.service.ClientService;
import com.maids.SpringBootQuiz.service.SalesService;
import com.maids.SpringBootQuiz.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/sales")
@RestController

public class SalesController {


    @Autowired
    SalesService salesService;

    @Autowired
    ClientService clientService;

    @Autowired
    SellerService sellerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SalesRequest salesRequest) {
        boolean client_exist = clientService.searchById(salesRequest.getClient());
        boolean seller_exist = sellerService.searchById(salesRequest.getSeller());

        if(! client_exist)
        {
            return ResponseEntity.badRequest().body("Client doesn't exist");

        }

        if (! seller_exist)
        {
            return ResponseEntity.badRequest().body("Seller doesn't exist");

        }
        salesService.create(salesRequest);
        return ResponseEntity.ok("Sale operation has been created successfully!");

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody List<SoldProductRequest> soldProductsRequest, @PathVariable Long id){
        if (salesService.searchById(id) ){
            salesService.update(soldProductsRequest,id);
            return ResponseEntity.ok("Sale Operation has been updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Sale Operation doesn't exist");
        }
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<SalesResponse> fetchAll()
    {
        return salesService.fetchAll();

    }
}
