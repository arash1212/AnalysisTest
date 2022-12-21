package com.example.salehi.dto.customer;

import com.example.salehi.dto.order.OrderOutput;
import com.example.salehi.model.entity.CustomerEntity;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class CustomerOutput {
    private final int id;
    private final String description;
    private final String email;
    private final String firstName;
    private final String lastName;

    private List<OrderOutput> orders;

    public CustomerOutput(CustomerEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.email = entity.getEmail();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();

        this.orders = entity.getOrders().stream().filter(Objects::nonNull).
                map(OrderOutput::new).collect(Collectors.toList());
    }
}
