package com.example.salehi.dto.product;

import com.example.salehi.model.entity.ProductEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductInput {
    @NotBlank
    @Size(max = 100)
    @Schema(example = "مثال نام محصول")
    private String name;
    @NotNull
    @Schema(example = "1300000")
    private Double price;

    public void fillEntity(ProductEntity entity) {
        entity.setName(this.name);
        entity.setPrice(this.price);
    }
}
