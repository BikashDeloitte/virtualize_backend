package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.ProductRepository;
import com.hu.Virtualize.repositories.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ProductCreateServiceImpl implements ProductCreateService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private AdminRepository adminRepository;

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
    public ShopEntity deleteProduct(ProductCommand productCommand) {
        Optional<ShopEntity> shopEntityOptional = shopRepository.findById(productCommand.getShopId());

        if(shopEntityOptional.isEmpty()) {
            log.error("Invalid Shop");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid shop");
        }
        ShopEntity shop = shopEntityOptional.get();

        Set<ProductEntity> shopProduct = new HashSet<>();
        boolean presentProductInShop = false;

        for(ProductEntity product : shop.getShopProducts()) {
            if(product.getProductId().equals(productCommand.getProductId())) {
                presentProductInShop = true;
            } else {
                shopProduct.add(product);
            }
        }

        if(!presentProductInShop) {
            log.error("Product isn't present in given shop");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product isn't present in given shop");
        }

        shop.setShopProducts(shopProduct);
        shop = shopRepository.save(shop);

        productRepository.deleteById(productCommand.getProductId());
        return shop;
    }
//    public Set<ShopEntity> getAllShopsByAdminId(Long id) {
//
//        Optional<AdminEntity> admin = adminRepository.findById(id);
//        if (admin == null) {
//            log.error("Invalid Admin ");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid admin");
//        }
//
//        Set<ShopEntity> shops = null;
//        List<AdminEntity> allAdmin = adminRepository.findAll();
//        for (int i = 0; i < allAdmin.size(); i++) {
//            if (allAdmin.get(i).getAdminId().equals(id)) {
//                shops= allAdmin.get(i).getAdminShops();
//            }
//        }
//        return shops;
//    }




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
