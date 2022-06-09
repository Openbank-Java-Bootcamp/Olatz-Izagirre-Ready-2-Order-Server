package com.ironhack.finalprojectserver.service.interfaces;

import com.ironhack.finalprojectserver.DTO.EatingTableDTO;
import com.ironhack.finalprojectserver.model.EatingTable;

import java.util.List;

public interface EatingTableServiceInterface {
    EatingTable saveEatingTable (EatingTableDTO eatingTableDTO);

    List<EatingTable> getEatingTables ();

    List<EatingTable> getEatingTablesByWaiter(String name);

    void updateEatingTable (Long id, EatingTableDTO eatingTableDTO);
}
