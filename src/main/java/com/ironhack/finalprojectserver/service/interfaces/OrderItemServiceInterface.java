package com.ironhack.finalprojectserver.service.interfaces;

import com.ironhack.finalprojectserver.DTO.OrderItemDTO;
import com.ironhack.finalprojectserver.model.OrderItem;

import java.util.List;

public interface OrderItemServiceInterface {
    OrderItem saveOrderItem (OrderItemDTO orderItemDTO);

    List<OrderItem> getOrderItems ();

    List<OrderItem> getVisibles ();

    void changeVisibility (Long id);
}
