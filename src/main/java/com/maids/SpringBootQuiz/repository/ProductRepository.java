package com.maids.SpringBootQuiz.repository;

import com.maids.SpringBootQuiz.model.Client;
import com.maids.SpringBootQuiz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);
    Optional<Product> findById(Long id);

}
