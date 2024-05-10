package com.WideAPI.WideTechnologiesAPI.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="order_cart")
public class OrderCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCartId;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonBackReference
    private Customer customer;


    @OneToMany(mappedBy = "orderCart", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("orderCart")
    private List<OrderCartItem> items = new ArrayList<>();
    private double totalPrice;

    public OrderCart() {

    }

    public double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getTotal())
                .sum();
    }
    public OrderCart(Customer customer, List<OrderCartItem> items) {
        this.customer = customer;
        this.items = items;
    }
}
