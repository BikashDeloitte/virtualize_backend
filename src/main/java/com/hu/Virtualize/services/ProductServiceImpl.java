package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This function will return all the product in the all shops
     * @return list of product
     */
    @Override
    public List<ProductEntity> getProduct() {
        List<ProductEntity> productEntityOptional = productRepository.findAll();

        return productEntityOptional;
    }


    /**
     * This function will insert the image in product entity
     * @param productId product id
     * @param multipartFile product image
     * @return status message
     */
    public String insertProductImage(Long productId, MultipartFile multipartFile) {
        ProductEntity productEntity = findProductById(productId);

        // convert MultipartFile into byte array and store in product entity
        Byte[] byteObjects;
        try{
            byteObjects = new Byte[multipartFile.getBytes().length];

            // copy the file data into byte array
            int i = 0;
            for (byte b : multipartFile.getBytes()){
                byteObjects[i++] = b;
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, e.getMessage());
        }

        // set the profile image
        productEntity.setProductImage(byteObjects);
        productEntity = productRepository.save(productEntity);

        return "Image update successfully for product: " + productEntity.getProductId();
    }

    /**
     * This function will return the product entity by productId
     * @param productId product id
     * @return product entity
     */
    public ProductEntity findProductById(Long productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);

        // if productId isn't valid
        if(productEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This productId isn't valid. Please enter valid productId");
        }
        return productEntity.get();
    }
}
