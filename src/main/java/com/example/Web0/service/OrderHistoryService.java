package com.example.Web0.service;

import com.example.Web0.entities.OrderHistoryEntity;

import java.util.List;

public interface OrderHistoryService {
    void addOrderHistory();
    List<OrderHistoryEntity> getOrderHistory();

}
