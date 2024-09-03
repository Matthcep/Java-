package com.parquesoftti.c26a.controller;

import com.parquesoftti.c26a.model.Customer;
import com.parquesoftti.c26a.model.Product;
import com.parquesoftti.c26a.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> findAll(){
        return  productService.findAll();

    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return  productService.findById(id);
    }

    @PostMapping
    public Product save(@RequestBody Product product){
        return  productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        return   productService.update(id,product);
    }

    @GetMapping("/search")
    public Product findByName(@RequestParam String name){
        return  productService.findByName(name);
    }
}
