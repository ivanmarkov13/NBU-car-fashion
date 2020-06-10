//package com.studio.carfashion.model;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "fabricated_parts")
//@NoArgsConstructor
//@Getter
//@Setter
//@AllArgsConstructor
//public class FabricatedPart {
//
//    @EmbeddedId
//    private CarFabricCompositeKey carFabricCompositeKey;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "part_id", nullable = false)
//    private CarPart carPart;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "fabric_id", nullable = false)
//    private Fabric fabric;
//
//    private Double fabricAmount;
//    private Double price;
//    private Integer hoursWorked;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "order", nullable = false)
//    private Order order;
//
//}
