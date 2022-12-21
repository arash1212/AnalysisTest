package com.example.salehi.dto.customer;

import com.example.salehi.model.entity.CustomerEntity;
import lombok.Getter;

@Getter
public class CustomerBriefOut {

    private final int id;
    private final String email;

    public CustomerBriefOut(CustomerEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
    }
}
