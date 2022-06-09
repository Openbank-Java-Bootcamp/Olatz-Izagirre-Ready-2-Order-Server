package com.ironhack.finalprojectserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EatingTableDTO {
    @Positive
    private Integer seats;
    @NotEmpty
    private String waiter;
}
