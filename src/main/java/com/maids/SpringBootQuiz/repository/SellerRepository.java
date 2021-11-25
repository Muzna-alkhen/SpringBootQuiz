package com.maids.SpringBootQuiz.repository;

import com.maids.SpringBootQuiz.model.Client;
import com.maids.SpringBootQuiz.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository  extends JpaRepository<Seller, Long> {
    Optional<Seller> findById(Long id);

}
