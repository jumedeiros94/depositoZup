package com.example.depositoZup.service;

import com.example.depositoZup.dtos.ProductDTO;
import com.example.depositoZup.model.ProductModel;
import com.example.depositoZup.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductModel>getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<ProductModel> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductModel createProduct(ProductDTO productDTO) {
        ProductModel productModel = new ProductModel();
        productModel.setName(productDTO.getName());
        productModel.setValue(productDTO.getValue());
        productModel.setAmount(productDTO.getAmount());
        productModel.setDescriptionProduct(productDTO.getDescriptionProduct());
        return productRepository.save(productModel);
    }

    public ProductModel updateProduct(ProductDTO productDTO) {
        Optional<ProductModel> productOptional = productRepository.findById(productDTO.getIdProduct());

        if (productOptional.isPresent()) {
            ProductModel productModel = productOptional.get();
            productModel.setName(productDTO.getName());
            productModel.setValue(productDTO.getValue());
            productModel.setAmount(productDTO.getAmount());
            productModel.setDescriptionProduct(productDTO.getDescriptionProduct());

            return productRepository.save(productModel);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteProduct(Long productId) {
        Optional<ProductModel> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            ProductModel productModel = productOptional.get();
            productRepository.delete(productModel);
        }else {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    }
}
