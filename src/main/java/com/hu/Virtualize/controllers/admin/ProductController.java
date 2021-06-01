package com.hu.Virtualize.controllers.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.services.admin.ProductCreateService;
import com.hu.Virtualize.services.admin.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/admin/product")
@RestController
public class ProductController {
    @Autowired
    private ProductCreateService productCreateService;

    @Autowired
    private ShopService shopService;

    /**
     * This api will help you to add new shop under admin.
     * @param productCommand shop and admin details.
     * @return admin details
     */
    @PostMapping("/create")
    public ResponseEntity<?> insertShop(@RequestBody ProductCommand productCommand) {
        AdminEntity admin = productCreateService.insertProduct(productCommand);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@RequestBody ProductCommand productCommand) {
        AdminEntity admin = productCreateService.updateProduct(productCommand);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteShop(@RequestBody ProductCommand productCommand) {
        log.info("delete product in shop");
        AdminEntity admin = productCreateService.deleteProduct(productCommand);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
}
