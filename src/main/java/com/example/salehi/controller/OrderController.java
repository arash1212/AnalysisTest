package com.example.salehi.controller;

import com.example.salehi.dto.order.OrderInput;
import com.example.salehi.dto.order.OrderOutput;
import com.example.salehi.model.service.OrderService;
import com.example.salehi.utils.PaginationFilter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create New Order")
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody @Valid OrderInput input) {
        this.orderService.save(input);
    }

    @Operation(summary = "Update Order By Id")
    @PutMapping(path = "/{customerId}/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = "customerId") int customerId, @PathVariable(name = "productId") int productId,
                       @RequestBody @Valid OrderInput input) {
        this.orderService.update(customerId, productId, input);
    }

    @Operation(summary = "Delete Order By Id")
    @DeleteMapping(path = "/{customerId}/{productId}")
    public void delete(@PathVariable(name = "customerId") int customerId, @PathVariable(name = "productId") int productId) {
        this.orderService.delete(customerId, productId);
    }

    @Operation(summary = "List Orders")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderOutput>> orders(@Valid PaginationFilter paginationFilter) {
        return ResponseEntity.ok(this.orderService.getAll(paginationFilter));
    }

    @Operation(summary = "List Orders By CustomerId")
    @GetMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderOutput>> ordersByCustomer(@PathVariable(name = "customerId") int id, @Valid PaginationFilter paginationFilter) {
        return ResponseEntity.ok(this.orderService.getAll(paginationFilter));
    }
}
