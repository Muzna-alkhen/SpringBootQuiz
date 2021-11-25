package com.maids.SpringBootQuiz.repository;

import com.maids.SpringBootQuiz.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {
    List<Client> findAllByFirstNameAndLastName(String firstName, String lastName);
    Optional<Client> findById(Long id);

}
