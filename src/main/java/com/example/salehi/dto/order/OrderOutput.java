package com.example.salehi.dto.order;

import com.example.salehi.dto.customer.CustomerBriefOut;
import com.example.salehi.dto.product.ProductBriefOut;
import com.example.salehi.model.entity.OrderEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class OrderOutput implements Serializable {
    private final CustomerBriefOut customer;
    private final ProductBriefOut product;
    private final Integer count;

    public OrderOutput(OrderEntity entity) {
        this.customer = new CustomerBriefOut(entity.getCustomer());
        this.product = new ProductBriefOut(entity.getProduct());
        this.count = entity.getCount();
    }
}
