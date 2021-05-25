package com.hu.Virtualize.controllers;

import com.hu.Virtualize.services.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/productCategory")
@RestController
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/stores")
    public ResponseEntity<?> getStoreName() {
        List<String> shopsName = productCategoryService.getStoreName();
        return new ResponseEntity<>(shopsName, HttpStatus.OK);
    }

    /**
     * This function will return all available products in all shops.
     * @return list of products.
     */
    @GetMapping("/products")
    public ResponseEntity<?> getProductNames() {
        List<String> productName = productCategoryService.getProductNames();
        return new ResponseEntity<>(productName, HttpStatus.OK);
    }
}
