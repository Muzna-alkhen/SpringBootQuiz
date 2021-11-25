package com.maids.SpringBootQuiz.service;

import com.maids.SpringBootQuiz.dto.ClientRequest;
import com.maids.SpringBootQuiz.dto.ClientResponse;
import com.maids.SpringBootQuiz.dto.ProductRequest;
import com.maids.SpringBootQuiz.dto.ProductResponse;
import com.maids.SpringBootQuiz.model.Category;
import com.maids.SpringBootQuiz.model.Client;
import com.maids.SpringBootQuiz.model.Product;
import com.maids.SpringBootQuiz.repository.CategoryRepository;
import com.maids.SpringBootQuiz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Product create(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCreationDate(new Date());
        Optional<Category> category = categoryRepository.findById(productRequest.getCategory());
        if (category.isPresent())
        {
            product.setCategory(category.get());
        }
        else
        {
            //suppose there is a default category in database id=1
            Optional<Category> defaultCategory = categoryRepository.findById(1L);
            product.setCategory(defaultCategory.get());
        }
        productRepository.save(product);
        return product;
    }

    public boolean searchByName(String name) {
        List<Product> products = productRepository.findAllByName(name);

        if (products.isEmpty()) {
            return false;
        }
        return true;

    }

    public boolean searchById(Long id) {
        Optional<Product> products = productRepository.findById(id);
        if (products.isEmpty())
            return false;
        return true;

    }

    public void update(ProductRequest productRequest, Long id) {

        Product updatedProduct = ( productRepository.findById(id)).get();
        updatedProduct.setName(productRequest.getName());
        updatedProduct.setDescription(productRequest.getDescription());
        Optional<Category> category = categoryRepository.findById(productRequest.getCategory());
        if (category.isPresent())
        {
            updatedProduct.setCategory(category.get());
        }
        productRepository.save(updatedProduct);
    }

    public List<ProductResponse> fetchAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product :
                products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setCreationDate(product.getCreationDate());
            productResponse.setDescription(product.getDescription());
            productResponse.setName(product.getName());
            productResponse.setCategory(product.getCategory().getId());
            productResponses.add(productResponse);
        }
        return productResponses;
    }
}
