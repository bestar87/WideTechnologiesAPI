package com.WideAPI.WideTechnologiesAPI.Controller;


import com.WideAPI.WideTechnologiesAPI.Entities.Customer;
import com.WideAPI.WideTechnologiesAPI.Entities.OrderCart;
import com.WideAPI.WideTechnologiesAPI.Entities.OrderCartItem;
import com.WideAPI.WideTechnologiesAPI.Entities.Product;
import com.WideAPI.WideTechnologiesAPI.Service.CustomerService;
import com.WideAPI.WideTechnologiesAPI.Service.ProductService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;
    private ProductService productService;


    @Autowired
    public CustomerController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @PostMapping("/addCustomers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createNewCustomers(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PostMapping("/{customerId}/addProductToCart")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToCart(@PathVariable Long customerId, @RequestParam Long productId, @RequestParam int quantity){
        Optional<Customer> customer = customerService.findCustomerById(customerId);
        Optional<Product> product = productService.findProductById(productId);
        if(customer.isPresent() && product.isPresent()){
            customerService.addProductToCart(customer.get(), product.get(), quantity);
        } else{
            throw  new RuntimeException("Customer or Product not found!");
        }
    }

    @GetMapping("/{customerId}/getAllOrderCartItems")
    public List<OrderCartItem> getAllOrderCartItems(@PathVariable Long customerId){
        Optional<Customer> optionalCustomer = customerService.findCustomerById(customerId);

        if(optionalCustomer.isPresent()){
            OrderCart orderCart = optionalCustomer.get().getOrderCart();

            if(orderCart != null){
                List<OrderCartItem> orderCartItems = orderCart.getItems();
                return orderCartItems;
            } else{
                throw new RuntimeException("There's no order cart for the customer with id: " + customerId);
            }
        } else{
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

    }

}

