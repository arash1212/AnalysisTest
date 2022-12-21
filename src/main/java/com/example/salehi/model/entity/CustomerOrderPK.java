package com.example.salehi.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class CustomerOrderPK implements Serializable {
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Column(name = "PRODUCT_ID")
    private Integer productId;
}
