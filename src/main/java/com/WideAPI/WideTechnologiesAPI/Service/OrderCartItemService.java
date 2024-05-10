package com.WideAPI.WideTechnologiesAPI.Service;

import com.WideAPI.WideTechnologiesAPI.Entities.OrderCart;
import com.WideAPI.WideTechnologiesAPI.Entities.OrderCartItem;
import com.WideAPI.WideTechnologiesAPI.Entities.Product;
import com.WideAPI.WideTechnologiesAPI.Repository.OrderCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCartItemService {


    private final OrderCartItemRepository orderCartItemRepository;

    @Autowired
    public OrderCartItemService(OrderCartItemRepository orderCartItemRepository) {
        this.orderCartItemRepository = orderCartItemRepository;
    }

    public OrderCartItem addItemToCart(OrderCart orderCart, Product product, int quantity){
        OrderCartItem item = new OrderCartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setTotal(product.getProductPrice() * quantity);
        item.setOrderCart(orderCart);

        return orderCartItemRepository.save(item);

    }
}

