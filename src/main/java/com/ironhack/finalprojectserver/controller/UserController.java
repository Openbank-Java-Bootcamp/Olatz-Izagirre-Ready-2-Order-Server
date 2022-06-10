package com.ironhack.finalprojectserver.controller;

import com.ironhack.finalprojectserver.model.User;
import com.ironhack.finalprojectserver.model.Waiter;
import com.ironhack.finalprojectserver.repository.WaiterRepository;
import com.ironhack.finalprojectserver.service.interfaces.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userServiceInterface.getUsers();
    }
}
