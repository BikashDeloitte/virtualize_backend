package com.hu.Virtualize.controllers.admin;

import com.hu.Virtualize.commands.admin.ShopCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.services.admin.ShopService;
import com.hu.Virtualize.services.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/admin/shop")
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;

    /**
     * This api will help you to add new shop under admin.
     * @param shopCommand shop and admin details.
     * @return admin details
     */
    @PostMapping("/create")
    public ResponseEntity<?> insertShop(@RequestBody ShopCommand shopCommand) {
        AdminEntity admin = shopService.insertShop(shopCommand);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@RequestBody ShopCommand shopCommand) {
        AdminEntity admin = shopService.updateShop(shopCommand);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteShop(@RequestBody ShopCommand shopCommand) {
        AdminEntity admin = shopService.deleteShop(shopCommand);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getShopsById(@PathVariable Long id){
        Set<ShopEntity> shops = shopService.getAllShopsByAdminId(id);
        return new ResponseEntity<>(shops,HttpStatus.OK);
    }
}
