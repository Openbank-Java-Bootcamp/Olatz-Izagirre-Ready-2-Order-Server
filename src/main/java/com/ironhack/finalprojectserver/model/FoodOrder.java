package com.ironhack.finalprojectserver.model;

import com.ironhack.finalprojectserver.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<OrderItem> orderItems;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn (name = "eating_table")
    private EatingTable eatingTable;
}
