package com.maids.SpringBootQuiz.controller;


import com.maids.SpringBootQuiz.dto.ClientRequest;
import com.maids.SpringBootQuiz.dto.ProductRequest;
import com.maids.SpringBootQuiz.dto.ProductResponse;
import com.maids.SpringBootQuiz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/product")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest) {

        if (productService.searchByName(productRequest.getName())) {
            return ResponseEntity.badRequest().body("Product already exists");
        } else {

            productService.create(productRequest);
            return ResponseEntity.ok("Product has been created successfully!");
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody ProductRequest productRequest, @PathVariable Long id){
        if (productService.searchById(id) ){
            productService.update(productRequest,id);
            return ResponseEntity.ok("Product has been updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Product doesn't exist");
        }

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProductResponse> fetchAll() {
        return productService.fetchAll();

    }


}
