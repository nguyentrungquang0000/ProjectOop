package com.example.Web0.controller;

import com.example.Web0.dto.request.ItemAddRequest;
import com.example.Web0.entities.CartItemEntity;
import com.example.Web0.service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartitem")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("permitAll()")
    @PostMapping("/add")
    public CartItemEntity addItem(@RequestBody ItemAddRequest request){
        return cartItemService.addItem(request);
    }
    @PreAuthorize("permitAll()")
    @DeleteMapping("/delete-{id}")
    public String deleteItem(@PathVariable long id){
        cartItemService.deletItem(id);
        return "Item deleted successfully";
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/update")
    public CartItemEntity updateItem(@RequestBody ItemAddRequest request){
        return cartItemService.updateItem(request);
    }


}
