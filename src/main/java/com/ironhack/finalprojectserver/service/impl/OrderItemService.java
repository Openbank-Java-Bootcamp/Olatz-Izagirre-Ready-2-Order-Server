package com.ironhack.finalprojectserver.service.impl;

import com.ironhack.finalprojectserver.DTO.OrderItemDTO;
import com.ironhack.finalprojectserver.model.OrderItem;
import com.ironhack.finalprojectserver.repository.ChefRepository;
import com.ironhack.finalprojectserver.repository.OrderItemRepository;
import com.ironhack.finalprojectserver.service.interfaces.OrderItemServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@Slf4j
public class OrderItemService implements OrderItemServiceInterface {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ChefRepository chefRepository;

    public OrderItem saveOrderItem(OrderItemDTO orderItemDTO) {
        if (orderItemRepository.findByName(orderItemDTO.getName()) != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Item already exists");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setName(orderItemDTO.getName());
        orderItem.setDescription(orderItemDTO.getDescription());
        orderItem.setImage(orderItemDTO.getImage());
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setChef(chefRepository.findByName(orderItemDTO.getChef()));
        orderItem.setVisible(true);
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }

    public List<OrderItem> getVisibles() {
        return orderItemRepository.findVisibles();
    }

    public void changeVisibility(Long id) {
        OrderItem orderItemFromDb = orderItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
        orderItemFromDb.setVisible(!orderItemFromDb.isVisible());
        orderItemRepository.save(orderItemFromDb);
    }
}
