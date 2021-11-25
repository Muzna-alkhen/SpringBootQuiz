package com.maids.SpringBootQuiz.service;

import com.maids.SpringBootQuiz.model.Client;
import com.maids.SpringBootQuiz.model.Seller;
import com.maids.SpringBootQuiz.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;
    public boolean searchById(Long id) {
        Optional<Seller> sellers = sellerRepository.findById(id);
        if (sellers.isEmpty())
            return false;
        return true;

    }

}
