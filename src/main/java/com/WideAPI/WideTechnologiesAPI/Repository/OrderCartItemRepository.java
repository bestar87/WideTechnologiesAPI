package com.WideAPI.WideTechnologiesAPI.Repository;

import com.WideAPI.WideTechnologiesAPI.Entities.OrderCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCartItemRepository extends JpaRepository<OrderCartItem, Long> {

}
