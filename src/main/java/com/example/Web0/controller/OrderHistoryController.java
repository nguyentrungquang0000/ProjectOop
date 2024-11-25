package com.example.Web0.controller;

import com.example.Web0.entities.OrderHistoryEntity;
import com.example.Web0.entities.OrderItemEntity;
import com.example.Web0.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/history/add")
    public void addHistory(){
        orderHistoryService.addOrderHistory();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/histories")
    public List<OrderHistoryEntity> getHistory(){
        return orderHistoryService.getOrderHistory();
    }
}
