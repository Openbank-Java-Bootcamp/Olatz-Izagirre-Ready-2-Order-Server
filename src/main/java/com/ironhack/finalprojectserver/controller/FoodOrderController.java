package com.ironhack.finalprojectserver.controller;


import com.ironhack.finalprojectserver.DTO.FoodOrderDTO;
import com.ironhack.finalprojectserver.model.FoodOrder;
import com.ironhack.finalprojectserver.service.interfaces.FoodOrderServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class FoodOrderController {

    @Autowired
    private FoodOrderServiceInterface foodOrderServiceInterface;

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

    @GetMapping("/foodOrders/ordered")
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
}
