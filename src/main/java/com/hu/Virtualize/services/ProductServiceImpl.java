package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> getProduct() {
        List<ProductEntity> productEntityOptional = productRepository.findAll();

        return productEntityOptional;
    }
}
