package com.example.salehi.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class PaginationFilter {
    @Min(0)
    private Integer from;
    @Min(1)
    @Max(100)
    @Schema(example = "10")
    private Integer size;
}
