package com.example.Web0.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAddRequest {
    private Long id;
    private Long productId;
    private Long quantity;
}
