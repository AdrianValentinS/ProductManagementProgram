package com.sample.group.springproductmanagementproject.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long productID;

    @Column
    private String productDescription;

    @Column
    private int productPricePerUnit;

    @Column
    private String productCategory;

    @Column
    private Long productQuantity;


}
