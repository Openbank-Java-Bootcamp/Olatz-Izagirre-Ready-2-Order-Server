package com.ironhack.finalprojectserver.controller;


import com.ironhack.finalprojectserver.DTO.EatingTableDTO;
import com.ironhack.finalprojectserver.DTO.FoodOrderDTO;
import com.ironhack.finalprojectserver.model.EatingTable;
import com.ironhack.finalprojectserver.model.FoodOrder;
import com.ironhack.finalprojectserver.repository.FoodOrderRepository;
import com.ironhack.finalprojectserver.service.interfaces.FoodOrderServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class FoodOrderController {

    @Autowired
    private FoodOrderServiceInterface foodOrderServiceInterface;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @PostMapping("/foodOrders")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodOrder saveFoodOrder(@RequestBody @Valid FoodOrderDTO foodOrderDTO) {
        return foodOrderServiceInterface.saveFoodOrder(foodOrderDTO);
    }

    @GetMapping("/foodOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodOrder> getFoodOrders() {
        return foodOrderServiceInterface.getFoodOrders();
    }

    @GetMapping("/foodOrders/status/ordered")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodOrder> getOrderedFoodOrders() {
        return foodOrderServiceInterface.getOrderedFoodOrders();
    }

    @GetMapping("/foodOrders/cooked")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodOrder> getCookedFoodOrders() {
        return foodOrderServiceInterface.getCookedFoodOrders();
    }

    @GetMapping("/foodOrders/served")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodOrder> getServedFoodOrders() {
        return foodOrderServiceInterface.getServedFoodOrders();
    }

    @GetMapping("/foodOrders/cooked/waiter")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodOrder> getWaitersCookedFoodOrders(@RequestParam String name) {
        return foodOrderServiceInterface.getWaitersCookedFoodOrders(name);
    }

    @GetMapping("/foodOrders/served/waiter")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodOrder> getWaitersServedFoodOrders(@RequestParam String name) {
        return foodOrderServiceInterface.getWaitersServedFoodOrders(name);
    }

    @PatchMapping("/foodOrders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void statusChange(@PathVariable Long id) {
        foodOrderServiceInterface.changeStatus(id);
    }

    @GetMapping("foodOrders/eatingTables/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodOrder> getOrdersByTable(@PathVariable Long id) {
        return foodOrderServiceInterface.getOrdersByTable(id);
    }

    @GetMapping("/foodOrders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodOrder getFoodOrdersById(@PathVariable Long id) {
        return foodOrderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @PutMapping("/foodOrders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFoodOrder(@PathVariable Long id, @RequestBody @Valid FoodOrderDTO foodOrderDTO) {
        foodOrderServiceInterface.updateFoodOrder(id, foodOrderDTO);
    }
}
