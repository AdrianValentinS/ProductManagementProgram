package com.sample.group.springproductmanagementproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private String productName;

    @Column
    private String productDescription;

    @Column
    private BigDecimal productPricePerUnit;

    @Column
    private String productCategory;

    @Column
    private Long productQuantity;

}
