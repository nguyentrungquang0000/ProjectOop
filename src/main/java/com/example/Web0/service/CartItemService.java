package com.example.Web0.service;

import com.example.Web0.dto.request.ItemAddRequest;
import com.example.Web0.entities.CartItemEntity;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

public interface CartItemService {
    CartItemEntity addItem(ItemAddRequest request);
    CartItemEntity updateItem(ItemAddRequest request);
    void deletItem(Long id);
}
