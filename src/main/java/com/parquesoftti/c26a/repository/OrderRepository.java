package com.parquesoftti.c26a.repository;

import com.parquesoftti.c26a.model.Customer;
import com.parquesoftti.c26a.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>  {
}
