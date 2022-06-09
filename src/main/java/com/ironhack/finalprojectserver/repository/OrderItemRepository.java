package com.ironhack.finalprojectserver.repository;

import com.ironhack.finalprojectserver.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByName(String name);


    @Query(value = "SELECT * FROM order_item WHERE visible = 1", nativeQuery = true)
    List<OrderItem> findVisibles();
}
