package com.example.Web0.repository;

import com.example.Web0.entities.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity,Long> {
}
