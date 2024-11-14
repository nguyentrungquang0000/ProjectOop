package com.example.Web0.repository;

import com.example.Web0.dto.request.ProductSearchRequest;
import com.example.Web0.entities.ProductEntity;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductEntity> findProduct(ProductSearchRequest request);
}
