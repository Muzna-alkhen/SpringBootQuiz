package com.maids.SpringBootQuiz.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        @Column
        private String name;

    @OneToMany(mappedBy="seller")
    private Set<SaleOperation> saleOperations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SaleOperation> getSaleOperations() {
        return saleOperations;
    }

    public void setSaleOperations(Set<SaleOperation> saleOperations) {
        this.saleOperations = saleOperations;
    }
}
