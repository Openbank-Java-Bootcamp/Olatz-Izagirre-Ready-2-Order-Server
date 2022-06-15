package com.ironhack.finalprojectserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrderDTO {
    @Positive(message = "Provide a valid table.")
    private Long tableId;

    private List<Long> itemsId;
}
