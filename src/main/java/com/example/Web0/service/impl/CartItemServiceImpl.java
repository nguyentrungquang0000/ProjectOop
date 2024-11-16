package com.example.Web0.service.impl;

import com.example.Web0.dto.request.ItemAddRequest;
import com.example.Web0.entities.CartEntity;
import com.example.Web0.entities.CartItemEntity;
import com.example.Web0.entities.UserEntity;
import com.example.Web0.repository.CartItemRepository;
import com.example.Web0.repository.ProductRepository;
import com.example.Web0.repository.UserRepository;
import com.example.Web0.repository.impl.CartRepository;
import com.example.Web0.service.CartItemService;
import com.example.Web0.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartItemEntity addItem(ItemAddRequest request) {
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user =userRepository.findByUsername(userName);
        CartEntity cart = user.getCart();
        CartItemEntity item= modelMapper.map(request,CartItemEntity.class);
        item.setCart(cart);
        item.setProduct(productRepository.findById(request.getProductId()).get());

        return cartItemRepository.save(item);
    }

    @Override
    public CartItemEntity updateItem(ItemAddRequest request) {
        CartItemEntity cartItemEntity= modelMapper.map(request,CartItemEntity.class);
        return cartItemRepository.save(cartItemEntity);
    }

    @Override
    public void deletItem(Long id) {
        cartItemRepository.deleteById(id);
        return;
    }
}
