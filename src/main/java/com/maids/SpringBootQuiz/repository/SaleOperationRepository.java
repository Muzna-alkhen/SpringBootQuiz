package com.maids.SpringBootQuiz.repository;

import com.maids.SpringBootQuiz.model.Client;
import com.maids.SpringBootQuiz.model.SaleOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleOperationRepository  extends JpaRepository<SaleOperation, Long> {
    Optional<SaleOperation> findById(Long id);

}
