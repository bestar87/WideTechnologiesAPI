package com.WideAPI.WideTechnologiesAPI.Repository;

import com.WideAPI.WideTechnologiesAPI.Entities.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long>  {

    OrderCart save(OrderCart orderCart);
}
