package com.ironhack.finalprojectserver.repository;

import com.ironhack.finalprojectserver.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    @Query(value = "SELECT * FROM food_order WHERE status = :status", nativeQuery = true)
    List<FoodOrder> findByStatus(@Param("status") String status);

    @Query(value = "SELECT * FROM food_order INNER JOIN eating_table ON food_order.eating_table=eating_table.id INNER JOIN waiter ON eating_table.waiter=waiter.id INNER JOIN user on waiter.id= user.id WHERE food_order.status = :status AND user.name=:waiter", nativeQuery = true)
    List<FoodOrder> findByStatusAndWaiter(@Param("status") String status,@Param("waiter")String name);
}
