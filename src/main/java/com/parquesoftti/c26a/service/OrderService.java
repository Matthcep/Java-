package com.parquesoftti.c26a.service;

import com.parquesoftti.c26a.model.Order;


import java.util.List;

public interface OrderService {
    public List<Order> findAll();
    public Order findById(Long id);
    public Order save(Order order);
    public Order update(Long id, Order order);
    public void delete(Long id);


}
