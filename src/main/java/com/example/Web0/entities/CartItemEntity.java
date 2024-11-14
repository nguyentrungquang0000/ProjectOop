package com.example.Web0.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "totalprice")
    private Long totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        if (product != null && quantity != null) {
            this.totalPrice = product.getPrice() * quantity;
        }
    }
}
