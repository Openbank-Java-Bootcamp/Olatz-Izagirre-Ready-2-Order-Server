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

    List<FoodOrder> getWaitersCookedFoodOrders (String name);

    List<FoodOrder> getWaitersServedFoodOrders (String name);

    void changeStatus (Long id);

    List<FoodOrder> getOrdersByTable(Long id);

    void updateFoodOrder (Long id, FoodOrderDTO foodOrderDTO);

    void deleteFoodOrder (Long id);
}
