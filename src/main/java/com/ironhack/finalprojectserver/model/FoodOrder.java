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
    @JoinTable(
            name = "order_items",
            joinColumns = {@JoinColumn (name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    private List<OrderItem> orderItems;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn (name = "eating_table")
    private EatingTable eatingTable;
}
