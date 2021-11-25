package com.maids.SpringBootQuiz.repository;

import com.maids.SpringBootQuiz.model.Client;
import com.maids.SpringBootQuiz.model.Product;
import com.maids.SpringBootQuiz.model.SaleOperation;
import com.maids.SpringBootQuiz.model.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldProductRepository  extends JpaRepository<SoldProduct, Long> {
    List<SoldProduct> findAllBySaleOperationAndProduct(SaleOperation saleOperation, Product product);

}
