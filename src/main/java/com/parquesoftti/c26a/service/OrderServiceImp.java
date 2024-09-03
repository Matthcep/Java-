package com.parquesoftti.c26a.service;

import com.parquesoftti.c26a.model.Customer;
import com.parquesoftti.c26a.model.Order;
import com.parquesoftti.c26a.model.Product;
import com.parquesoftti.c26a.repository.CustomerRepository;
import com.parquesoftti.c26a.repository.OrderRepository;
import com.parquesoftti.c26a.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{

    final OrderRepository orderRepository;
    final ProductRepository productRepository;
    final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public Order save(Order order) {

        Product product = productRepository.findById(order.getProduct().getProductId()).orElseThrow(()->new RuntimeException("product not found"));
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId()).orElseThrow(()->new RuntimeException("customer not found"));
        order.setCustomer(customer);
        order.setProduct(product);
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public Order update(Long id, Order order) {

         Order order1 = orderRepository.findById(id).orElseThrow(()->new RuntimeException("order not found"));
        Product product = productRepository.findById(order.getProduct().getProductId()).orElseThrow(()->new RuntimeException("product not found"));
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId()).orElseThrow(()->new RuntimeException("customer not found"));
        order1.setCustomer(customer);
        order1.setProduct(product);
        order1.setOrderDate(new Date());
        order1.setQuantity(order.getQuantity());
        return orderRepository.save(order1);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        orderRepository.deleteById(id);

    }
}
