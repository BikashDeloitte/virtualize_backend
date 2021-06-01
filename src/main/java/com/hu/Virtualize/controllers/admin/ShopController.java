package com.hu.Virtualize.controllers.admin;

import com.hu.Virtualize.commands.admin.ShopCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.services.admin.ShopService;
import com.hu.Virtualize.services.user.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@Slf4j
@RequestMapping("/admin/shop")
@RestController
@CrossOrigin("*")
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


    /**
     * This api will insert image for specific shop
     * @param shopId shop Id
     * @param multipartFile image for shop
     * @return 200 OK status
     */

    @PostMapping("/insertImage/{shopId}")
    public ResponseEntity<String> insertShopImage(@PathVariable String shopId, @RequestParam("image") MultipartFile multipartFile) {
        log.info("Admin try to change the shop image");
        String status = shopService.insertShopImage(Long.valueOf(shopId), multipartFile);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/image/{shopId}")
    public void renderImageFromDB(@PathVariable String shopId, HttpServletResponse response) {
        ShopEntity shopEntity = shopService.findShopById(Long.valueOf(shopId));

        try {
            byte[] byteArray = new byte[shopEntity.getShopImage().length];

            int i = 0;
            for (Byte wrappedByte : shopEntity.getShopImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            log.error("Image fetch error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
