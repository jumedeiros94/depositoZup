package com.example.depositoZup.controller;

import com.example.depositoZup.dtos.ProductDTO;
import com.example.depositoZup.model.ProductModel;
import com.example.depositoZup.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Api(tags = "Deposito Zup API")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get product by ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    public Optional<ProductModel> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new product", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso")
    })
    public ResponseEntity<ProductModel>createProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductModel productModel = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
    }

    @PutMapping("/update")
    @Operation(summary = "Update a product", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso")
    })
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductModel productModel = productService.updateProduct(productDTO);
        return ResponseEntity.ok(productModel);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product by ID", method = "DELETE")
    public ResponseEntity<String>deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Produto deletado com sucesso");
    }


}
