package com.ironhack.finalprojectserver.controller;

import com.ironhack.finalprojectserver.DTO.OrderItemDTO;
import com.ironhack.finalprojectserver.model.OrderItem;
import com.ironhack.finalprojectserver.service.interfaces.OrderItemServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderItemController {
    @Autowired
    private OrderItemServiceInterface orderItemServiceInterface;

    @PostMapping("/orderItems")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItem saveOrderItem(@RequestBody @Valid OrderItemDTO orderItemDTO) {
        return orderItemServiceInterface.saveOrderItem(orderItemDTO);
    }

    @GetMapping("/orderItems")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem> getOrderItems() {
        return orderItemServiceInterface.getOrderItems();
    }

    @GetMapping("/orderItems/visibles")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem> getVisibles() {
        return orderItemServiceInterface.getVisibles();
    }

    @PatchMapping("/orderItems/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void visibilityChange(@PathVariable Long id) {
        orderItemServiceInterface.changeVisibility(id);
    }
}
