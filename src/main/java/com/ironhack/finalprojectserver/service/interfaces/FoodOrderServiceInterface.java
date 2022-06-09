package com.ironhack.finalprojectserver.service.interfaces;

import com.ironhack.finalprojectserver.DTO.EatingTableDTO;
import com.ironhack.finalprojectserver.DTO.FoodOrderDTO;
import com.ironhack.finalprojectserver.model.EatingTable;
import com.ironhack.finalprojectserver.model.FoodOrder;

import java.util.List;

public interface FoodOrderServiceInterface {
    FoodOrder saveFoodOrder (FoodOrderDTO foodOrderDTO);

    List<FoodOrder> getFoodOrders ();

    List<FoodOrder> getOrderedFoodOrders();

    List<FoodOrder> getCookedFoodOrders();

    List<FoodOrder> getServedFoodOrders();

    void changeStatus (Long id);
}
