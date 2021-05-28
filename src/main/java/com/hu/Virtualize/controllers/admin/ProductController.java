package com.hu.Virtualize.controllers.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.services.admin.ProductCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/product")
@RestController
public class ProductController {
    @Autowired
    private ProductCreateService productCreateService;

    /**
     * This api will help you to add new shop under admin.
     * @param productCommand shop and admin details.
     * @return admin details
     */
    @PostMapping("/create")
    public ResponseEntity<?> insertShop(@RequestBody ProductCommand productCommand) {
        ShopEntity shop = productCreateService.insertProduct(productCommand);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@RequestBody ProductCommand productCommand) {
        ShopEntity shop = productCreateService.updateProduct(productCommand);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteShop(@RequestBody ProductCommand productCommand) {
        String status = productCreateService.deleteProduct(productCommand);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
