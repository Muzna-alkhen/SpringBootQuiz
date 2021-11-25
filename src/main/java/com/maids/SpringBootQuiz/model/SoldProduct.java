package com.maids.SpringBootQuiz.model;


import javax.persistence.*;

@Entity
@Table(name = "sold_product")
public class SoldProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @Column
    private float price;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=true)
    private Product product;

    @ManyToOne
    @JoinColumn(name="sales_operation_id", nullable=true)
    private SaleOperation saleOperation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SaleOperation getSaleOperation() {
        return saleOperation;
    }

    public void setSaleOperation(SaleOperation saleOperation) {
        this.saleOperation = saleOperation;
    }
}
