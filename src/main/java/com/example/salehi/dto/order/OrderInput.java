package com.example.salehi.dto.order;

import com.example.salehi.model.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderInput {
    @NotNull
    private Integer customerId;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer count;

    public void fillEntity(OrderEntity entity) {
        entity.setCount(this.count);
    }
}
