package com.example.depositoZup;

import com.example.depositoZup.dtos.ProductDTO;
import com.example.depositoZup.model.ProductModel;
import com.example.depositoZup.repository.ProductRepository;
import com.example.depositoZup.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductModel> mockProducts = new ArrayList<>();
        mockProducts.add(new ProductModel(1L, "Camiseta Nike", BigDecimal.valueOf(300.00), "Camiseta esportiva Masculino", 10));
        mockProducts.add(new ProductModel(2L, "Camiseta Adidas", BigDecimal.valueOf(200.00), "Camiseta esportiva feminina", 10));

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<ProductModel> result = productService.getAllProducts();

        assertEquals(mockProducts.size(), result.size());
        assertEquals(mockProducts.get(0), result.get(0));
        assertEquals(mockProducts.get(1), result.get(1));
    }

    @Test
    public void testGetProductById() {
        ProductModel mockProduct = new ProductModel(1L, "Top Adidas", BigDecimal.valueOf(150.00), "Top Feminino", 7);
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Optional<ProductModel> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals(mockProduct, result.get());
    }

    @Test
    public void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO(1L, "Bermuda Puma", BigDecimal.valueOf(300.00), "Bermuda Masculina", 10);

        ProductModel createdProduct = new ProductModel(1L, productDTO.getName(), productDTO.getValue(), productDTO.getDescriptionProduct(), productDTO.getAmount());
        when(productRepository.save(any(ProductModel.class))).thenReturn(createdProduct);

        ProductModel result = productService.createProduct(productDTO);

        assertEquals(createdProduct.getIdProduct(), result.getIdProduct());
        assertEquals(createdProduct.getName(), result.getName());
        assertEquals(createdProduct.getValue(), result.getValue());
        assertEquals(createdProduct.getDescriptionProduct(), result.getDescriptionProduct());
        assertEquals(createdProduct.getAmount(),result.getAmount());
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO productDTO = new ProductDTO(1L, "Bermuda Puma", BigDecimal.valueOf(300.00), "Bermuda Masculina", 10);

        ProductModel existingProduct = new ProductModel(1L, "Camiseta Puma", BigDecimal.valueOf(150.00), "Camiseta infantil", 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

        ProductModel updatedProduct = new ProductModel(1L, productDTO.getName(), productDTO.getValue(), productDTO.getDescriptionProduct(), productDTO.getAmount());
        when(productRepository.save(any(ProductModel.class))).thenReturn(updatedProduct);

        ProductModel result = productService.updateProduct(productDTO);

        verify(productRepository).findById(1L);
        verify(productRepository).save(any(ProductModel.class));

        assertEquals(updatedProduct.getIdProduct(), result.getIdProduct());
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getValue(), result.getValue());
        assertEquals(updatedProduct.getDescriptionProduct(), result.getDescriptionProduct());
        assertEquals(updatedProduct.getAmount(), result.getAmount());
    }


    @Test
    public void testDeleteProduct() {
        ProductModel existingProduct = new ProductModel();
        existingProduct.setIdProduct(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(existingProduct);
    }
}
