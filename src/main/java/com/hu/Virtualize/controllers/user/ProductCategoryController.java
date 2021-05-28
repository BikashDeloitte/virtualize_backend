package com.hu.Virtualize.controllers.user;

import com.hu.Virtualize.services.user.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
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
