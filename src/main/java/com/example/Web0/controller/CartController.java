package com.example.Web0.controller;

import com.example.Web0.entities.CartEntity;
import com.example.Web0.entities.CartItemEntity;
import com.example.Web0.entities.UserEntity;
import com.example.Web0.repository.UserRepository;
import com.example.Web0.repository.impl.CartRepository;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @PermitAll
    @GetMapping("/cart-item")
    public List<CartItemEntity> cartItem() {
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(userName);
        CartEntity cartEntity= userEntity.getCart();
        List<CartItemEntity> CartItems= cartEntity.getCartItems();
        return CartItems;
    }

}
