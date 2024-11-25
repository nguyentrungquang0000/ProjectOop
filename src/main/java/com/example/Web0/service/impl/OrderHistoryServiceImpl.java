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
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUsername(userName);
        CartEntity cartEntity = userEntity.getCart();
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
        orderHistoryEntity.setUser(userEntity);
        orderHistoryEntity.setCreatedAt(new Date());
        orderHistoryEntity.setTotalAmount(cartEntity.getCartItems().stream().mapToLong(item -> item.getTotalPrice()).sum());
        orderHistoryRepository.save(orderHistoryEntity);
        if (cartEntity.getCartItems().isEmpty()) {
            throw new RuntimeException("Giỏ hàng rỗng!");
        }
        List<OrderItemEntity> listItem = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            OrderItemEntity orderItemEntity = modelMapper.map(cartItemEntity, OrderItemEntity.class);
            orderItemEntity.setOrderHistory(orderHistoryEntity);
            listItem.add(orderItemEntity);
            orderItemEntity.setId(null);
            orderItemRepository.save(orderItemEntity);
        }

        orderHistoryEntity.setOrderItems(listItem);
        orderHistoryRepository.save(orderHistoryEntity);

        cartItemRepository.deleteAll(cartEntity.getCartItems());
        cartEntity.getCartItems().clear();
        cartRepository.save(cartEntity);


    }

    @Override
    public List<OrderHistoryEntity> getOrderHistory() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        UserEntity userEntity = userRepository.findByUsername(userName);
        return orderHistoryRepository.findByUserId(userEntity.getId(), sort);
    }
}
