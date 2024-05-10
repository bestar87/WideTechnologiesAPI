package com.WideAPI.WideTechnologiesAPI.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productType;
    private double productPrice;
    private int quantity;


    public Product() {

    }

    public Product(Long productId, String productName, String productType, double productPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}
