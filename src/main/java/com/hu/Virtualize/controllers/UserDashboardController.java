package com.hu.Virtualize.controllers;

import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.services.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestMapping("/product")
@RestController
public class UserDashboardController {
    private final ProductService productService;

    public UserDashboardController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"","/"})
    public ResponseEntity<?> getProduct() {
        List<ProductEntity> products = productService.getProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/insertImage/{productId}")
    public ResponseEntity<String> insertProductImage(@PathVariable String productId, @RequestParam("image") MultipartFile multipartFile) {
        String status = productService.insertProductImage(Long.valueOf(productId), multipartFile);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/image/{productId}")
    public void renderImageFromDB(@PathVariable String productId, HttpServletResponse response) throws IOException {
        ProductEntity productEntity = productService.findProductById(Long.valueOf(productId));

        // if image isn't available, then it will set the default image
        if (productEntity.getProductImage() == null) {
            // get the image in resources folder
            BufferedImage bImage = ImageIO.read(new File("src/main/resources/static/images/cloth.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );

            // convert into byte array
            byte[] byteData = bos.toByteArray();

            // convert  byte[] into Byte[]
            Byte[] bytesImage = new Byte[byteData.length];

            int i = 0;
            for (byte data : byteData) {
                bytesImage[i++] = data; //Autoboxing
            }

            // set the image in product entity
            productEntity.setProductImage(bytesImage);
        }

        byte[] byteArray = new byte[productEntity.getProductImage().length];

        int i = 0;
        for (Byte wrappedByte : productEntity.getProductImage()){
            byteArray[i++] = wrappedByte; //auto unboxing
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}

///ResponseStatusException