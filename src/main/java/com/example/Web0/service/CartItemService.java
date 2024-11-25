package com.example.Web0.service;

import com.example.Web0.dto.request.ItemAddRequest;
import com.example.Web0.entities.CartItemEntity;

public interface CartItemService {
    CartItemEntity addItem(ItemAddRequest request);
    CartItemEntity updateItem(ItemAddRequest request);
    void deleteItem(Long id);
    CartItemEntity getItem(Long id);
}
