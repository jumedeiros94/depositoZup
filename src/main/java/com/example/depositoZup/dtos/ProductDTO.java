package com.example.depositoZup.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long idProduct;
    @NotBlank
    private String name;
    @DecimalMin(value = "0.01")
    private BigDecimal value;
    private String descriptionProduct;
    @Min(value = 0)
    private int amount;
}
