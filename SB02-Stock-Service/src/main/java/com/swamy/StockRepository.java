package com.swamy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swamy.payload.OrderEvent;

public interface StockRepository extends JpaRepository<OrderEvent, Integer> {

}

