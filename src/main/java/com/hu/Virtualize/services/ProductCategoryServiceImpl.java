package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.repositories.ShopRepository;
import com.hu.Virtualize.repositories.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;

    public ProductCategoryServiceImpl(ShopRepository shopRepository, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    /**
     * This function will return all shop name.
     * @return list of shop name
     */
    @Override
    public List<String> getStoreName() {
        List<ShopEntity> shopList = shopRepository.findAll();

        Set<String> shopNames = new HashSet();

        for(ShopEntity shop : shopList) {
            shopNames.add(shop.getShopName());
        }

        List<String> shops = new ArrayList<>(shopNames);
        Collections.sort(shops);

        return shops;
    }

    /**
     * This function will return the all product names in shops.
     * @return list of product.
     */
    public List<String> getProductNames() {
        List<ProductEntity> productList = productRepository.findAll();

        Set<String> productNames = new HashSet<>();

        for(ProductEntity product: productList) {
            productNames.add(product.getProductName());
        }

        List<String> products = new ArrayList<>(productNames);
        Collections.sort(products);

        return products;
    }
}
