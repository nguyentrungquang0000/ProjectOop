package com.example.Web0.service.impl;

import com.example.Web0.configuration.ModelMapperConfig;
import com.example.Web0.dto.request.ProductRequest;
import com.example.Web0.dto.request.ProductSearchRequest;
import com.example.Web0.entities.ProductEntity;
import com.example.Web0.repository.ProductRepository;
import com.example.Web0.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductEntity addProduct(ProductEntity request) {
        ProductEntity productEntity = modelMapper.map(request, ProductEntity.class);
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity updateProduct(ProductRequest request) {
        ProductEntity productEntity= modelMapper.map(request, ProductEntity.class);
        return productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ;
    }


    @Override
    public List<ProductEntity> searchProduct(ProductSearchRequest request) {
        List<ProductEntity> productEntities = productRepository.findProduct(request);
        return productEntities;
    }


}
