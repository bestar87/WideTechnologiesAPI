package com.WideAPI.WideTechnologiesAPI.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="address")
    private String address;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("customer")
    private OrderCart orderCart;

    public Customer() {

    }

    public Customer(Long customerId, String customerName, String address, OrderCart orderCart) {
        this.customerName = customerName;
        this.address = address;
        this.orderCart = orderCart;
    }
}
