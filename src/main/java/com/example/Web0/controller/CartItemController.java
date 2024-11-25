package com.example.Web0.controller;

import com.example.Web0.dto.request.ItemAddRequest;
import com.example.Web0.entities.CartItemEntity;
import com.example.Web0.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartitem")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public CartItemEntity addItem(@RequestBody ItemAddRequest request) {
        return cartItemService.addItem(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/delete-{id}")
    public String deleteItem(@PathVariable long id) {
        cartItemService.deleteItem(id);
        return "Item deleted successfully";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/update")
    public CartItemEntity updateItem(@RequestBody ItemAddRequest request) {
        return cartItemService.updateItem(request);
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/{id}")
    public CartItemEntity getCartItemByProductId(@PathVariable Long id) {
        return cartItemService.getItem(id);
    }
}
