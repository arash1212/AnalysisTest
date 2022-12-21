package com.example.salehi.controller;

import com.example.salehi.dto.product.ProductInput;
import com.example.salehi.dto.product.ProductOutput;
import com.example.salehi.model.service.ProductService;
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
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create New Product")
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> save(@RequestBody @Valid ProductInput input) {
        return ResponseEntity.ok(this.productService.save(input));
    }

    @Operation(summary = "Update Product By Id")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = "id") int id, @RequestBody @Valid ProductInput input) {
        this.productService.update(id, input);
    }

    @Operation(summary = "Delete Product By Id")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        this.productService.delete(id);
    }

    @Operation(summary = "Get Product By Id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductOutput> customer(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(this.productService.getById(id));
    }

    @Operation(summary = "List Products")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductOutput>> customers(@Valid PaginationFilter paginationFilter) {
        return ResponseEntity.ok(this.productService.getAll(paginationFilter));
    }
}
