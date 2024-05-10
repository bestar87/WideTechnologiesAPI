package com.WideAPI.WideTechnologiesAPI.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="order_cart_item")
public class OrderCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_cart_item_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double total;

    @ManyToOne
    @JoinColumn(name = "order_cart_id")
    @JsonBackReference
    private OrderCart orderCart;

    public OrderCartItem() {

    }

    public OrderCartItem(Product product, int quantity, double total) {
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }
}
