package com.ironhack.finalprojectserver.service.impl;

import com.ironhack.finalprojectserver.DTO.FoodOrderDTO;
import com.ironhack.finalprojectserver.enums.Status;
import com.ironhack.finalprojectserver.model.EatingTable;
import com.ironhack.finalprojectserver.model.FoodOrder;
import com.ironhack.finalprojectserver.model.OrderItem;
import com.ironhack.finalprojectserver.repository.EatingTableRepository;
import com.ironhack.finalprojectserver.repository.FoodOrderRepository;
import com.ironhack.finalprojectserver.repository.OrderItemRepository;
import com.ironhack.finalprojectserver.service.interfaces.FoodOrderServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FoodOrderService implements FoodOrderServiceInterface {

    @Autowired
    private FoodOrderRepository foodOrderRepository;
    @Autowired
    private EatingTableRepository eatingTableRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public FoodOrder saveFoodOrder(FoodOrderDTO foodOrderDTO) {
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setEatingTable(eatingTableRepository.findById(foodOrderDTO.getTableId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found")));
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < foodOrderDTO.getItemsId().size(); i++) {
            OrderItem orderItem = orderItemRepository.findById(foodOrderDTO.getItemsId().get(i)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
            orderItems.add(orderItem);
            total = total.add(orderItem.getPrice());
        }
        foodOrder.setOrderItems(orderItems);
        foodOrder.setTotal(total);
        foodOrder.setStatus(Status.ORDERED);
        return foodOrderRepository.save(foodOrder);
    }

    public List<FoodOrder> getFoodOrders() {
        return foodOrderRepository.findAll();
    }

    public List<FoodOrder> getOrderedFoodOrders() {
        return foodOrderRepository.findByStatus(Status.ORDERED.toString());
    }

    public List<FoodOrder> getCookedFoodOrders() {
        return foodOrderRepository.findByStatus(Status.COOKED.toString());
    }

    public List<FoodOrder> getServedFoodOrders() {
        return foodOrderRepository.findByStatus(Status.SERVED.toString());
    }

    public List<FoodOrder> getWaitersCookedFoodOrders(String name) {
        return foodOrderRepository.findByStatusAndWaiter(Status.COOKED.toString(), name);
    }

    public List<FoodOrder> getWaitersServedFoodOrders(String name) {
        return foodOrderRepository.findByStatusAndWaiter(Status.SERVED.toString(), name);
    }

    public void changeStatus(Long id) {
        FoodOrder foodOrderFromDb = foodOrderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        if (foodOrderFromDb.getStatus() == Status.ORDERED) {
            foodOrderFromDb.setStatus(Status.COOKED);
            foodOrderRepository.save(foodOrderFromDb);
        } else if (foodOrderFromDb.getStatus() == Status.COOKED) {
            foodOrderFromDb.setStatus(Status.SERVED);
            foodOrderRepository.save(foodOrderFromDb);
        } else if (foodOrderFromDb.getStatus() == Status.SERVED) {
            foodOrderFromDb.setStatus(Status.PAID);
            foodOrderRepository.save(foodOrderFromDb);
        }
    }

    public List<FoodOrder> getOrdersByTable(Long id) {
        return foodOrderRepository.findByEatingTable(id);
    }

    public void updateFoodOrder (Long id, FoodOrderDTO foodOrderDTO){
        FoodOrder foodOrderFromDb= foodOrderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setId(foodOrderFromDb.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < foodOrderDTO.getItemsId().size(); i++) {
            OrderItem orderItem = orderItemRepository.findById(foodOrderDTO.getItemsId().get(i)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
            orderItems.add(orderItem);
            total = total.add(orderItem.getPrice());
        }
        foodOrder.setOrderItems(orderItems);
        foodOrder.setTotal(total);
        foodOrder.setStatus(foodOrderFromDb.getStatus());
        foodOrder.setEatingTable(foodOrderFromDb.getEatingTable());
        foodOrderRepository.save(foodOrder);
    }

    public void deleteFoodOrder (Long id){
        foodOrderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        foodOrderRepository.deleteById(id);
    }
}
