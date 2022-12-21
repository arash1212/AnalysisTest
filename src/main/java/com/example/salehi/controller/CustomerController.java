package com.example.salehi.controller;

import com.example.salehi.dto.customer.CustomerInput;
import com.example.salehi.dto.customer.CustomerOutput;
import com.example.salehi.model.service.CustomerService;
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
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create New Customer")
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> save(@RequestBody @Valid CustomerInput input) {
        return ResponseEntity.ok(this.customerService.save(input));
    }

    @Operation(summary = "Update Customer By Id")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = "id") int id, @RequestBody @Valid CustomerInput input) {
        this.customerService.update(id, input);
    }

    @Operation(summary = "Delete Customer By Id")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        this.customerService.delete(id);
    }

    @Operation(summary = "Get Customer By Id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerOutput> customer(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(this.customerService.getById(id));
    }

    @Operation(summary = "List Customers")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerOutput>> customers(@Valid PaginationFilter paginationFilter) {
        return ResponseEntity.ok(this.customerService.getAll(paginationFilter));
    }
}
