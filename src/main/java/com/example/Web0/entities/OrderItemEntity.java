package com.example.Web0.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderHistoryEntity orderHistory;

    @ManyToOne
    @JoinColumn(name = "product_id")

    private ProductEntity product;

    private Long quantity;

    private Long price;


}

