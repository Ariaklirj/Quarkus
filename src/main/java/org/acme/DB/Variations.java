package org.acme.DB;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Variations extends PanacheEntity {
    public String name;
    public String brand;
    public String sku;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "variation_generator")
    @SequenceGenerator(name="variation_generator", sequenceName = "vari_seq", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int stock;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    public Products product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }


}
