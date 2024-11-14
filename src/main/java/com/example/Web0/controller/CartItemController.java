package com.example.Web0.controller;

import com.example.Web0.dto.request.ItemAddRequest;
import com.example.Web0.entities.CartItemEntity;
import com.example.Web0.service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/addItem")
    public CartItemEntity addItem(@RequestBody ItemAddRequest request){
        return cartItemService.addItem(request);
    }
}
