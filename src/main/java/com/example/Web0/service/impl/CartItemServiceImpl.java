package com.example.Web0.service.impl;

import com.example.Web0.dto.request.ItemAddRequest;
import com.example.Web0.entities.CartEntity;
import com.example.Web0.entities.CartItemEntity;
import com.example.Web0.entities.UserEntity;
import com.example.Web0.repository.CartItemRepository;
import com.example.Web0.repository.ProductRepository;
import com.example.Web0.repository.UserRepository;
import com.example.Web0.service.CartItemService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartItemEntity addItem(ItemAddRequest request) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        CartEntity cart = user.getCart();
        CartItemEntity item = modelMapper.map(request, CartItemEntity.class);
        item.setCart(cart);
        item.setProduct(productRepository.findById(request.getProductId()).get());

        return cartItemRepository.save(item);
    }

    @Override
    public CartItemEntity updateItem(ItemAddRequest request) {
        CartItemEntity cartItemEntity = cartItemRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found!"));
        cartItemEntity.setQuantity(request.getQuantity() + cartItemEntity.getQuantity());
        return cartItemRepository.save(cartItemEntity);
    }

    @Override
    public void deleteItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItemEntity getItem(Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        CartEntity cart = user.getCart();
        CartItemEntity cartItemEntity = cart.getCartItems().stream()
                .filter(item -> Objects.equals(item.getProduct().getId(), id))
                .findFirst().orElse(null);
        return cartItemEntity;
    }
}
