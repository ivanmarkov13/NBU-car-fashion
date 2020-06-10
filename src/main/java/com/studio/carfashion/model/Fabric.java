package com.studio.carfashion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fabrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fabric {

    @Id
    private Integer id;

    private String material;
    private Double pricePerSquareMeter;
    private String origin;
    private String description;
    private Double amountInStock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company", nullable = false)
    private Company company;

}
