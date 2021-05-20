package com.hu.Virtualize.controllers;

import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDashboardController {
    private final ProductService productService;

    public UserDashboardController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct() {
        List<ProductEntity> products = productService.getProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

//ResponseStatusException