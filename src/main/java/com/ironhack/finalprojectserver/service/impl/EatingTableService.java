package com.ironhack.finalprojectserver.service.impl;

import com.ironhack.finalprojectserver.DTO.EatingTableDTO;
import com.ironhack.finalprojectserver.model.EatingTable;
import com.ironhack.finalprojectserver.model.Waiter;
import com.ironhack.finalprojectserver.repository.EatingTableRepository;
import com.ironhack.finalprojectserver.repository.WaiterRepository;
import com.ironhack.finalprojectserver.service.interfaces.EatingTableServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class EatingTableService implements EatingTableServiceInterface {

    @Autowired
    private EatingTableRepository eatingTableRepository;
    @Autowired
    private WaiterRepository waiterRepository;

    public EatingTable saveEatingTable(EatingTableDTO eatingTableDTO) {
        EatingTable eatingTable = new EatingTable();
        eatingTable.setSeats(eatingTableDTO.getSeats());
        eatingTable.setWaiter(waiterRepository.findByName(eatingTableDTO.getWaiter()));
        return eatingTableRepository.save(eatingTable);
    }

    public List<EatingTable> getEatingTables (){
        return eatingTableRepository.findAll();
    }

    public List<EatingTable> getEatingTablesByWaiter (String name){
        Waiter waiter = waiterRepository.findByName(name);
        return eatingTableRepository.findByWaiter(waiter);
    }

    public void updateEatingTable (Long id, EatingTableDTO eatingTableDTO){
        EatingTable eatingTableFromDb= eatingTableRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found"));
        EatingTable eatingTable = new EatingTable();
        eatingTable.setId(eatingTableFromDb.getId());
        eatingTable.setSeats(eatingTableDTO.getSeats());
        eatingTable.setWaiter(waiterRepository.findByName(eatingTableDTO.getWaiter()));
        eatingTableRepository.save(eatingTable);
    }
}
