package com.example.Web0.controller;

import com.example.Web0.dto.request.ProductRequest;
import com.example.Web0.dto.request.ProductSearchRequest;
import com.example.Web0.entities.ProductEntity;
import com.example.Web0.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public ProductEntity addProduct(@RequestBody ProductEntity request) {
        return productService.addProduct(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update")
    public ProductEntity updateProduct(@RequestBody ProductRequest request) {
        return productService.updateProduct(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete-{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<ProductEntity> searchProduct(@ModelAttribute ProductSearchRequest request) {
        return productService.searchProduct(request);
    }
}
