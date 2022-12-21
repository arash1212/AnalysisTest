package com.example.salehi.dto.product;

import com.example.salehi.model.entity.ProductEntity;
import lombok.Getter;

@Getter
public class ProductBriefOut {
    private final int id;
    private final String name;

    public ProductBriefOut(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
