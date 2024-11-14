package com.example.Web0.repository;

import com.example.Web0.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long>, ProductRepositoryCustom {

}
