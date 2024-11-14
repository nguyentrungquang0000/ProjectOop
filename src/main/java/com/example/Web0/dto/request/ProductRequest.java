package com.example.Web0.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {
    private Long id;
    private String name;
    private String title;
    private Long calories;
    private Long price;
    private Long weight;

}
