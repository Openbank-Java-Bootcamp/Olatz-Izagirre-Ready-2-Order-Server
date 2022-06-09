package com.ironhack.finalprojectserver.service.impl;

import com.ironhack.finalprojectserver.model.Role;
import com.ironhack.finalprojectserver.repository.RoleRepository;
import com.ironhack.finalprojectserver.service.interfaces.RoleServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleService implements RoleServiceInterface {
    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        log.info("Saving a new role {} to the database",role.getName());
        return roleRepository.save(role);
    }


}
