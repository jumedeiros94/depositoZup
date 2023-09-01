package com.example.depositoZup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 100, message = "O nome do produto não pode ter mais de 100 caracteres")
    private String name;

    @NotNull(message = "O valor do produto é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor do produto deve ser no mínimo 0.01")
    private BigDecimal value;

    @NotBlank(message = "A descrição do produto é obrigatória")
    @Size(max = 500, message = "A descrição do produto não pode ter mais de 500 caracteres")
    private String descriptionProduct;

    @Min(value = 0, message = "A quantidade do produto não pode ser negativa")
    private int amount;
}
