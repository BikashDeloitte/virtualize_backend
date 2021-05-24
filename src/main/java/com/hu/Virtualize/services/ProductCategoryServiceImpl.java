package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ShopRepository shopRepository;

    public ProductCategoryServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
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

        return shopNames.stream().collect(Collectors.toList());
    }
}
