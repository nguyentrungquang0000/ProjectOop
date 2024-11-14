package com.example.Web0.service;

import com.example.Web0.dto.request.ProductRequest;
import com.example.Web0.dto.request.ProductSearchRequest;
import com.example.Web0.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity addProduct(ProductEntity request);
    ProductEntity updateProduct(ProductRequest request);
    void deleteProduct(Long id);
    List<ProductEntity> searchProduct(ProductSearchRequest request);
}
