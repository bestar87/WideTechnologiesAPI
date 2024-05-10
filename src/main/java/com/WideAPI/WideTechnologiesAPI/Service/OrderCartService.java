package com.WideAPI.WideTechnologiesAPI.Service;


import com.WideAPI.WideTechnologiesAPI.Entities.Customer;
import com.WideAPI.WideTechnologiesAPI.Entities.OrderCart;
import com.WideAPI.WideTechnologiesAPI.Entities.OrderCartItem;
import com.WideAPI.WideTechnologiesAPI.Repository.OrderCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCartService {

    private OrderCartRepository orderCartRepository;

    @Autowired
    public OrderCartService(OrderCartRepository orderCartRepository) {
        this.orderCartRepository = orderCartRepository;
    }

    public OrderCart createOrderCartForCustomer(Customer customer) {
        OrderCart orderCart = new OrderCart();
        orderCart.setCustomer(customer);

        return orderCartRepository.save(orderCart);
    }

    public OrderCart updateOrderCart(OrderCart orderCart){
        return orderCartRepository.save(orderCart);
    }

    public double calculateTotalPrice(OrderCart orderCart) {
        double totalPrice = 0.0;

        for (OrderCartItem item : orderCart.getItems()) {
            totalPrice += item.getTotal();
        }

        return totalPrice;
    }


}
