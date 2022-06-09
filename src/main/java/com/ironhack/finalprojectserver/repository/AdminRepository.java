package com.ironhack.finalprojectserver.repository;

import com.ironhack.finalprojectserver.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
