package com.example.depositoZup;

import com.example.depositoZup.controller.ProductController;
import com.example.depositoZup.dtos.ProductDTO;
import com.example.depositoZup.model.ProductModel;
import com.example.depositoZup.service.ProductService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() throws Exception {
        ProductModel product1 = new ProductModel(1L, "Boné Adidas", BigDecimal.valueOf(99.00), "Boné coleção 2023", 5);
        ProductModel product2 = new ProductModel(2L, "Camiseta Puma", BigDecimal.valueOf(89.00), "Camiseta esportiva", 2);
        List<ProductModel>products = List.of(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(products.size()));
    }


    @Test
    void testGetProductById() throws Exception {
        ProductModel product = new ProductModel(1L, "Bermuda Nike", BigDecimal.valueOf(150.00), "Bermuda soccer", 10);

        when(productService.getProductById(anyLong())).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idProduct").value(product.getIdProduct()));
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO(1L, "Calça Nike", BigDecimal.valueOf(300.00), "Calça esportiva masculina", 5);

        when(productService.createProduct(any())).thenReturn(new ProductModel(1L, productDTO.getName(), productDTO.getValue(), productDTO.getDescriptionProduct(), productDTO.getAmount()));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Calça Nike\",\"value\":300.00,\"amount\":5,\"descriptionProduct\":\"Calça esportiva masculina\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idProduct").exists());///////////
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO(1L, "Top Nike", BigDecimal.valueOf(40.00), "Top esportivo", 10);

        when(productService.updateProduct(any())).thenReturn(new ProductModel(productDTO.getIdProduct(), productDTO.getName(), productDTO.getValue(), productDTO.getDescriptionProduct(), productDTO.getAmount()));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idProduct\":1,\"name\":\"Top Nike\",\"value\":40.0,\"amount\":10,\"descriptionProduct\":\"Top esportivo\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idProduct").value(productDTO.getIdProduct()));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(productService, times(1)).deleteProduct(1L);
    }

}



