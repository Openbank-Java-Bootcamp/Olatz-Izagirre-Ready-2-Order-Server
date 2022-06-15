package com.ironhack.finalprojectserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    @NotEmpty(message = "Provide a valid name.")
    private String name;
    @NotEmpty(message = "Provide a valid description.")
    private String description;
    @NotEmpty(message = "Provide a valid image.")
    private String image;
    @NotNull(message = "Provide a valid price.")
    @Positive(message = "Provide a valid price.")
    private BigDecimal price;
    private String chef;
}
