package com.example.Web0.service.impl;

import com.example.Web0.entities.*;
import com.example.Web0.repository.CartItemRepository;
import com.example.Web0.repository.OrderHistoryRepository;
import com.example.Web0.repository.OrderItemRepository;
import com.example.Web0.repository.UserRepository;
import com.example.Web0.repository.impl.CartRepository;
import com.example.Web0.service.OrderHistoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addOrderHistory() {
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(userName);
        CartEntity cartEntity= userEntity.getCart();
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
        orderHistoryEntity.setUser(userEntity);
        orderHistoryEntity.setTotalAmount(cartEntity.getCartItems().stream().mapToLong(item->item.getTotalPrice()).sum());
        orderHistoryRepository.save(orderHistoryEntity);
        List<OrderItemEntity> listItem= new ArrayList<>();
        for(CartItemEntity cartItemEntity: cartEntity.getCartItems()) {
            OrderItemEntity orderItemEntity = modelMapper.map(cartItemEntity, OrderItemEntity.class);
            orderItemEntity.setOrderHistory(orderHistoryEntity);
            listItem.add(orderItemEntity);
            orderItemRepository.save(orderItemEntity);
            cartItemRepository.deleteById(cartItemEntity.getCartItemId());
        }
        orderHistoryEntity.setOrderItems(listItem);
        orderHistoryRepository.save(orderHistoryEntity);

    }

    @Override
    public List<OrderHistoryEntity> getOrderHistory() {
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(userName);
        return userEntity.getOrderHistories();
    }
}
