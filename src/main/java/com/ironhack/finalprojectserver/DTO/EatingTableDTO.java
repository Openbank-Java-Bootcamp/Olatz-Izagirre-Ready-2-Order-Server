package com.ironhack.finalprojectserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EatingTableDTO {
    @NotNull(message = "Provide a valid number of seats.")
    @Positive(message = "Provide a valid number of seats.")
    private Integer seats;
    @NotEmpty(message = "Provide a valid waiter.")
    private String waiter;
}
