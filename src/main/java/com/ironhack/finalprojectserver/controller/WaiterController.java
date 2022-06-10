package com.ironhack.finalprojectserver.controller;

import com.ironhack.finalprojectserver.model.OrderItem;
import com.ironhack.finalprojectserver.model.Waiter;
import com.ironhack.finalprojectserver.repository.WaiterRepository;
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
public class WaiterController {

    @Autowired
    private WaiterRepository waiterRepository;

    @GetMapping("/waiters")
    @ResponseStatus(HttpStatus.OK)
    public List<Waiter> getWaiters() {
        return waiterRepository.findAll();
    }


}
