package com.example.Web0.repository.impl;

import com.example.Web0.dto.request.ProductSearchRequest;
import com.example.Web0.entities.ProductEntity;
import com.example.Web0.repository.ProductRepository;
import com.example.Web0.repository.ProductRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ProductEntity> findProduct(ProductSearchRequest request) {
        String sql = "SELECT * FROM products WHERE 1 = 1 ";
        if (request.getName() != null && !request.getName().isEmpty()) {
            sql += " AND name LIKE '%" + request.getName() + "%' ";
        }
        if (request.getMinPrice() != null) {
            sql += " AND price >= " + request.getMinPrice();
        }
        if (request.getMaxPrice() != null) {
            sql += " AND price <= " + request.getMaxPrice();
        }
        Query query = entityManager.createNativeQuery(sql, ProductEntity.class);
        return query.getResultList();
    }
}
