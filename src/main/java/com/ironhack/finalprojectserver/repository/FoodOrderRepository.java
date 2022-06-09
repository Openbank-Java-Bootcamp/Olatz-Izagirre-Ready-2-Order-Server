package com.ironhack.finalprojectserver.repository;

import com.ironhack.finalprojectserver.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    @Query(value = "SELECT * FROM food_order WHERE status = :status", nativeQuery = true)
    List<FoodOrder> findByStatus(@Param("status") String status);
}
