package com.maids.SpringBootQuiz.dto;

import java.util.List;

public class SalesRequest {

    private Long client;
    private Long seller;
    private List<SoldProductRequest> products;

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public List<SoldProductRequest> getProducts() {
        return products;
    }

    public void setProducts(List<SoldProductRequest> products) {
        this.products = products;
    }
}
