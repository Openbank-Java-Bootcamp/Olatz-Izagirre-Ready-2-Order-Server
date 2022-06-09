package com.ironhack.finalprojectserver.repository;

import com.ironhack.finalprojectserver.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaiterRepository extends JpaRepository<Waiter,Long> {
}
