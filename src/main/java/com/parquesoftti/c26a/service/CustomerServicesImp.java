package com.parquesoftti.c26a.service;

import com.parquesoftti.c26a.model.Customer;
import com.parquesoftti.c26a.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServicesImp implements CustomerService{
    final  CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {

        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public Customer update(Long id, Customer customer) {
        Customer customersTmp = customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("customer not found"));
        customersTmp.setCustomerName(customer.getCustomerName());
        customersTmp.setEmail(customer.getEmail());
        customersTmp.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(customersTmp);
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findByName(String name) {
        return customerRepository.findByCustomerName(name)
                .orElseThrow(()->new RuntimeException("customer not found"));
    }
}