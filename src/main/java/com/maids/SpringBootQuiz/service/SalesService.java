package com.maids.SpringBootQuiz.service;

import com.maids.SpringBootQuiz.controller.SalesController;
import com.maids.SpringBootQuiz.dto.SalesRequest;
import com.maids.SpringBootQuiz.dto.SalesResponse;
import com.maids.SpringBootQuiz.dto.SoldProductRequest;
import com.maids.SpringBootQuiz.model.*;
import com.maids.SpringBootQuiz.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SalesService {

    Logger logger = LoggerFactory.getLogger(SalesService.class);


    @Autowired
    SaleOperationRepository saleOperationRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SoldProductRepository soldProductRepository;


    @Transactional
    public void create(SalesRequest salesRequest)
    {
        SaleOperation saleOperation = new SaleOperation();
        //set current date as creation date
        saleOperation.setCreationDate(new Date());
        saleOperationRepository.save(saleOperation);
        logger.info("save the creation date of the sale operation");

        //fetch then set the client to sale operation
        Optional<Client> client = clientRepository.findById(salesRequest.getClient());
        saleOperation.setClient(client.get());
        saleOperationRepository.save(saleOperation);
        logger.info("save the client of the sale operation");


        //fetch then set the seller to sale operation
        Optional<Seller> seller = sellerRepository.findById(salesRequest.getSeller());
        saleOperation.setSeller(seller.get());
        saleOperationRepository.save(saleOperation);
        logger.info("save the seller of the sale operation");


        //fetch each requested product and add it to a list
        Set<SoldProduct> soldProducts = new HashSet();
        float total = 0;
        for (SoldProductRequest soldProductRequest:
             salesRequest.getProducts()) {

            SoldProduct soldProduct = new SoldProduct();
            Optional<Product> product = productRepository.findById(soldProductRequest.getProduct());

            soldProduct.setProduct(product.get());
            soldProduct.setPrice(soldProductRequest.getPrice());
            soldProduct.setQuantity(soldProductRequest.getQuantity());
            soldProduct.setSaleOperation(saleOperation);


            soldProductRepository.save(soldProduct);
            soldProducts.add(soldProduct);
            total += soldProductRequest.getPrice();

        }

        saleOperation.setSoldProducts(soldProducts);
        saleOperationRepository.save(saleOperation);
        logger.info("save the sold products of the sale operation");

        saleOperation.setTotal(total);
        saleOperationRepository.save(saleOperation);
        logger.info("save the total of the sale operation");




    }

    public List<SalesResponse> fetchAll() {
        List <SaleOperation> salesOperations = new ArrayList();
        saleOperationRepository.findAll().iterator().forEachRemaining(salesOperations::add);
        List <SalesResponse> salesResponses = new ArrayList();

        for (SaleOperation sale:
                salesOperations
             )
        {
            SalesResponse salesResponse = new SalesResponse();
            salesResponse.setId(sale.getId());
            salesResponse.setClient(sale.getClient().getId());
            salesResponse.setSeller(sale.getSeller().getId());
            salesResponse.setCreationDate(sale.getCreationDate());
            salesResponse.setTotal(sale.getTotal());
            salesResponses.add(salesResponse);

        }
        return salesResponses;
    }
    public boolean searchById(Long id) {
        Optional<SaleOperation> saleOperation = saleOperationRepository.findById(id);
        if (saleOperation.isEmpty())
            return false;
        return true;

    }

    public void update(List<SoldProductRequest> soldProductsRequest, Long id) {
        // fetch the requested sale operation
        SaleOperation saleOperation = (saleOperationRepository.findById(id)).get();

        float updatedTotal = 0;

        //fetch each requested product in the sale operation
        for (SoldProductRequest soldProductRequest:
             soldProductsRequest) {

            //fetch the product
            Product product = (productRepository.findById(soldProductRequest.getProduct())).get();
            SoldProduct updatedSoldProduct = (soldProductRepository.findAllBySaleOperationAndProduct
                    (saleOperation,product)).get(0);

            //update quantity and price
            updatedSoldProduct.setQuantity(soldProductRequest.getQuantity());
            updatedSoldProduct.setPrice(soldProductRequest.getPrice());
            soldProductRepository.save(updatedSoldProduct);
            updatedTotal += soldProductRequest.getPrice();

        }
        //update the total of sale operation
        saleOperation.setTotal(updatedTotal);
        saleOperationRepository.save(saleOperation);

    }
}
