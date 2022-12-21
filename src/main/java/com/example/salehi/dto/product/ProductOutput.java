package com.example.salehi.dto.product;

import com.example.salehi.dto.order.OrderOutput;
import com.example.salehi.model.entity.ProductEntity;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class ProductOutput implements Serializable {
    private final int id;
    private final String name;
    private final Double price;
    private final List<OrderOutput> orders;

    public ProductOutput(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();

        this.orders = entity.getOrders().stream().filter(Objects::nonNull).
                map(OrderOutput::new).collect(Collectors.toList());
    }
}
