package com.maids.SpringBootQuiz.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sale_operation")
public class SaleOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = true)
    private Date creationDate;

    @Column (nullable = true)
    private float total=0;

    @OneToMany(mappedBy="saleOperation")
    private Set<SoldProduct> soldProducts;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=true)
    private Client client;

    @ManyToOne
    @JoinColumn(name="seller_id", nullable=true)
    private Seller seller;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Set<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<SoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
