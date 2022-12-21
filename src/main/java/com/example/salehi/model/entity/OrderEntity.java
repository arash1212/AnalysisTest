package com.example.salehi.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_ORDER", indexes = {
        @Index(name = "PRODUCT_IDX_FK", columnList = "PRODUCT_ID_FK"),
        @Index(name = "CUSTOMER_IDX_FK", columnList = "CUSTOMER_ID_FK")
})
public class OrderEntity {

    @EmbeddedId
    private CustomerOrderPK id;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "CUSTOMER_ID_FK", nullable = false)
    private CustomerEntity customer;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID_FK", nullable = false)
    private ProductEntity product;
    @Column(name = "COUNT", nullable = false)
    private int count;
}
