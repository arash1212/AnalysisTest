package com.example.salehi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TB_PRODUCT", indexes = {
        @Index(name = "PRODUCT_IDX_PK", columnList = "ID")
})
public class ProductEntity {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "productSequence", sequenceName = "PRODUCT_SEQUENCE",
            initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
    private Long id;
    @Column(name = "NAME", length = 100)
    private String name;
    @Column(name = "PRICE")
    private double price;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<OrderEntity> orders;
}
