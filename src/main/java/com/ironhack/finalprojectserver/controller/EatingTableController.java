package com.ironhack.finalprojectserver.controller;

import com.ironhack.finalprojectserver.DTO.EatingTableDTO;
import com.ironhack.finalprojectserver.model.EatingTable;
import com.ironhack.finalprojectserver.repository.EatingTableRepository;
import com.ironhack.finalprojectserver.service.interfaces.EatingTableServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class EatingTableController {
    @Autowired
    private EatingTableServiceInterface eatingTableServiceInterface;
    @Autowired
    private EatingTableRepository eatingTableRepository;

    @PostMapping("/eatingTables")
    @ResponseStatus(HttpStatus.CREATED)
    public EatingTable saveEatingTable(@RequestBody @Valid EatingTableDTO eatingTableDTO) {
        return eatingTableServiceInterface.saveEatingTable(eatingTableDTO);
    }

    @GetMapping("/eatingTables")
    @ResponseStatus(HttpStatus.OK)
    public List<EatingTable> getEatingTables() {
        return eatingTableServiceInterface.getEatingTables();
    }

    @GetMapping("/eatingTables/waiter")
    @ResponseStatus(HttpStatus.OK)
    public List<EatingTable> getEatingTablesByWaiter(@RequestParam String name) {
        return eatingTableServiceInterface.getEatingTablesByWaiter(name);
    }

    @PutMapping("/eatingTables/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEatingTable(@PathVariable Long id, @RequestBody @Valid EatingTableDTO eatingTableDTO) {
        eatingTableServiceInterface.updateEatingTable(id, eatingTableDTO);
    }

}
