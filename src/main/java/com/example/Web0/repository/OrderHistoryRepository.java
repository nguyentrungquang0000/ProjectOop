package com.example.Web0.repository;

import com.example.Web0.entities.OrderHistoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity,Long> {
    List<OrderHistoryEntity> findByUserId(Long userId, Sort sort);
}
