package com.WideAPI.WideTechnologiesAPI.Service;


import com.WideAPI.WideTechnologiesAPI.Entities.Customer;
import com.WideAPI.WideTechnologiesAPI.Entities.OrderCart;
import com.WideAPI.WideTechnologiesAPI.Entities.OrderCartItem;
import com.WideAPI.WideTechnologiesAPI.Entities.Product;
import com.WideAPI.WideTechnologiesAPI.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final OrderCartService orderCartService;
    private final OrderCartItemService orderCartItemService;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, OrderCartService orderCartService, OrderCartItemService orderCartItemService) {
        this.customerRepository = customerRepository;
        this.orderCartService = orderCartService;
        this.orderCartItemService = orderCartItemService;
    }

    public void findAllProductFromOrderCart(Long customerId){

    }

    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        OrderCart orderCart = new OrderCart();
        orderCart.setCustomer(savedCustomer);

        OrderCart savedOrderCart = orderCartService.createOrderCartForCustomer(customer);
        savedCustomer.setOrderCart(savedOrderCart);
        return savedCustomer;
    }

    public void addProductToCart(Customer customer, Product product, int quantity) {
        OrderCart orderCart = customer.getOrderCart();
        OrderCartItem item = orderCartItemService.addItemToCart(orderCart, product, quantity);
        orderCart.getItems().add(item);
        double totalPrice = orderCart.calculateTotalPrice();
        orderCart.setTotalPrice(totalPrice);
        orderCartService.updateOrderCart(orderCart);
    }

    public Optional<Customer> findCustomerById(Long customerId){
        return customerRepository.findById(customerId);
    }
}
