package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.repositories.ProductRepository;
import com.hu.Virtualize.repositories.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class ProductCreateServiceImpl implements ProductCreateService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    /**
     * This function will insert the product in shop.
     * @param productCommand product details.
     * @return
     */
    @Override
    public ShopEntity insertProduct(ProductCommand productCommand) {
        Optional<ShopEntity> shopEntityOptional = shopRepository.findById(productCommand.getShopId());

        if(shopEntityOptional.isEmpty()) {
            log.error("Invalid Shop");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid shop");
        }

        ShopEntity shop = shopEntityOptional.get();

        ProductEntity product = new ProductEntity();
        product = convert(product, productCommand);

        shop.getShopProducts().add(product);

        shop = shopRepository.save(shop);
        return shop;
    }

    @Override
    public ShopEntity updateProduct(ProductCommand productCommand) {
        Optional<ShopEntity> shopEntityOptional = shopRepository.findById(productCommand.getShopId());

        if(shopEntityOptional.isEmpty()) {
            log.error("Invalid Shop");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid shop");
        }
        ShopEntity shop = shopEntityOptional.get();

        ProductEntity shopProduct = null;

        for(ProductEntity product: shop.getShopProducts()) {
            if(product.getProductId().equals(productCommand.getProductId())) {
                shopProduct = product;

                shopProduct = convert(shopProduct, productCommand);
                break;
            }
        }

        // if product is not present in the shop.
        if(shopProduct == null) {
            log.error("Invalid Product");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Product");
        }

        // update product value
        shop.getShopProducts().add(shopProduct);
        shop = shopRepository.save(shop);
        return shop;
    }

    @Override
    public String deleteProduct(ProductCommand productCommand) {
        return null;
    }

    ProductEntity convert(ProductEntity productEntity, ProductCommand productCommand) {
        ProductEntity product = productEntity;

        // update all details
        if(productCommand.getProductName() != null) {
            product.setProductName(productCommand.getProductName());
        }

        if(productCommand.getProductPrice() != null) {
            product.setProductPrice(productCommand.getProductPrice());
        }
        if(productCommand.getBrandName() != null) {
            product.setBrandName(productCommand.getBrandName());
        }
        if(productCommand.getCategoryType() != null) {
            product.setCategoryType(productCommand.getCategoryType());
        }
        if(productCommand.getProductType() != null) {
            product.setProductType(productCommand.getProductType());
        }
        if(productCommand.getProductDescription() != null) {
            product.setProductDescription(productCommand.getProductDescription());
        }
        if(productCommand.getProductImage() != null) {
            product.setProductImage(productCommand.getProductImage());
        }

        return product;
    }
}
