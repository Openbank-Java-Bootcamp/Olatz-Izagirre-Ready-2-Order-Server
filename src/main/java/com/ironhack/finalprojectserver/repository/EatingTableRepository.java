package com.ironhack.finalprojectserver.repository;

import com.ironhack.finalprojectserver.model.EatingTable;
import com.ironhack.finalprojectserver.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EatingTableRepository extends JpaRepository<EatingTable,Long> {
    List<EatingTable> findByWaiter (Waiter waiter);
}
