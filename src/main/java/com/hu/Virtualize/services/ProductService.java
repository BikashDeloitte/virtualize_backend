package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getProduct();
    ProductEntity findProductById(Long productId);
    String insertProductImage(Long productId, MultipartFile multipartFile);
}
