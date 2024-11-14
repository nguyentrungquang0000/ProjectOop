package com.example.Web0.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchRequest {
    private String name;
    private Long maxPrice;
    private Long minPrice;
}
