package com.ironhack.finalprojectserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EatingTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive
    private Integer seats;
    @ManyToOne
    @JoinColumn (name = "waiter")
    private Waiter waiter;
    @OneToMany (mappedBy = "eatingTable")
    @JsonIgnore
    private List<FoodOrder> orders;
}
