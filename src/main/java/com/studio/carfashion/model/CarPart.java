package com.studio.carfashion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "car_parts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarPart {

    @Id
    private int id;

    private String name;
    private Double priceMultiplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company", nullable = false)
    private Company company;

}
