package com.ironhack.finalprojectserver.repository;

import com.ironhack.finalprojectserver.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<Chef,Long> {
    Chef findByName (String name);
}
