package com.hu.Virtualize.bootstrap;

import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.DiscountEntity;
import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.entities.RecommendEntity;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.enums.ProductEnum;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.RecommendRepository;
import com.hu.Virtualize.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Slf4j
@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RecommendRepository recommendRepository;

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public UserBootstrap(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Start user bootstrap class ");
        testDataBase();
    }

    public void testDataBase() {
        // add user1 in database
        UserEntity user1 = new UserEntity("Zhatab","z@gmail.com",passwordEncoder.encode("123"),null);
        UserEntity user2 = new UserEntity("Praveen", "p@gmail.com",passwordEncoder.encode("123"),null);
        UserEntity user3 = new UserEntity("Bikash", "b@gmail.com",passwordEncoder.encode("123"),null);
        UserEntity user4 = new UserEntity("Shahansk", "s@gmail.com",passwordEncoder.encode("123"),null);

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);
        user4 = userRepository.save(user4);

//        ------------------------------------------------------------------------------------------------------------

        ShopEntity clothShop1 = new ShopEntity("Peter England", getImage("src/main/resources/static/images/brand.jpg"));
        clothShop1.setShopImage(getImage("src/main/resources/static/images/PeterEngland.png"));

        ShopEntity clothShop2 = new ShopEntity("Raymond", getImage("src/main/resources/static/images/brand.jpg"));
        clothShop1.setShopImage(getImage("src/main/resources/static/images/RaymondShop.png"));

        ShopEntity clothShop3 = new ShopEntity("Allen Solly", getImage("src/main/resources/static/images/brand.jpg"));
        clothShop1.setShopImage(getImage("src/main/resources/static/images/LMShop.png"));

        // create cloth products
        ProductEntity cloth1 = new ProductEntity("Shirt", 1200,null, ProductEnum.CLOTHS.toString(),"Male",null);
        cloth1.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));
        cloth1.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Diwali",5,"2021-06-06"),new DiscountEntity("Holi",15,"2021-06-06"))));

        ProductEntity cloth2 = new ProductEntity("Jeans", 1500,null, ProductEnum.CLOTHS.toString(),"Female",null);
        cloth2.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));
        cloth2.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Eid",50,"2021-06-06"),new DiscountEntity("Special",15,"2021-06-06"))));

        ProductEntity cloth3 = new ProductEntity("Tie", 1600,null, ProductEnum.CLOTHS.toString(),"Unisex",null);
        cloth3.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));
        cloth3.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Diwali",25,"2021-06-06"),new DiscountEntity("Holi",15,"2021-06-06"))));

        ProductEntity cloth4 = new ProductEntity("T-shirt", 2000,null, ProductEnum.CLOTHS.toString(),"Unisex",null);
        cloth4.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));
        cloth4.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Diwali",30,"2021-06-06"),new DiscountEntity("Holi",15,"2021-06-06"))));

        ProductEntity cloth5 = new ProductEntity("Shirt", 500,null, ProductEnum.CLOTHS.toString(),"Female",null);
        cloth5.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));

        ProductEntity cloth6 = new ProductEntity("Jeans", 900,null, ProductEnum.CLOTHS.toString(),"Female",null);
        cloth6.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));
        cloth6.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Diwali",70,"2021-06-06"),new DiscountEntity("Holi",15,"2021-06-06"))));

        ProductEntity cloth7 = new ProductEntity("Tie", 10000,null, ProductEnum.CLOTHS.toString(),"Unisex",null);
        cloth7.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));

        ProductEntity cloth8 = new ProductEntity("T-shirt", 3000,null, ProductEnum.CLOTHS.toString(),"Male",null);
        cloth8.setProductImage(getImage("src/main/resources/static/images/cloth.jpg"));

        cloth1.setBrandName(clothShop1.getShopName());
        cloth2.setBrandName(clothShop1.getShopName());
        cloth3.setBrandName(clothShop1.getShopName());
        clothShop1.setShopProducts(new HashSet<>(Arrays.asList(cloth1,cloth2,cloth3)));

        cloth4.setBrandName(clothShop2.getShopName());
        cloth5.setBrandName(clothShop2.getShopName());
        cloth6.setBrandName(clothShop2.getShopName());
        clothShop2.setShopProducts(new HashSet<>(Arrays.asList(cloth4,cloth5,cloth6)));

        cloth7.setBrandName(clothShop3.getShopName());
        cloth8.setBrandName(clothShop3.getShopName());
        clothShop3.setShopProducts(new HashSet<>(Arrays.asList(cloth7,cloth8)));

//       Complete cloth shops -------------------------------------------------------------------------------

        // medicine shop -------------------------------------------------------------------------------------------------

        ShopEntity medicineShop1 = new ShopEntity("Aurobindo Pharma", getImage("src/main/resources/static/images/brand.jpg"));
        medicineShop1.setShopImage(getImage("src/main/resources/static/images/medicine.jpg"));

        ShopEntity medicineShop2 = new ShopEntity("Pharmaceutical Pharma", getImage("src/main/resources/static/images/brand.jpg"));
        medicineShop2.setShopImage(getImage("src/main/resources/static/images/MedicalShop.png"));

        ShopEntity medicineShop3 = new ShopEntity("ManKind Pharma", getImage("src/main/resources/static/images/brand.jpg"));
        medicineShop3.setShopImage(getImage("src/main/resources/static/images/MedicalShop.png"));

        ProductEntity medicine1 = new ProductEntity("Paracetamol",100,medicineShop1.getShopName(),ProductEnum.MEDICINE.toString(),null,null);
        medicine1.setProductImage(getImage("src/main/resources/static/images/medicine.jpg"));
        medicine1.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Covid",70,"2021-06-06"),new DiscountEntity("White fungus",15,"2021-06-06"))));

        ProductEntity medicine2 = new ProductEntity("Penicillin",1000,medicineShop1.getShopName(),ProductEnum.MEDICINE.toString(),null,null);
        medicine2.setProductImage(getImage("src/main/resources/static/images/medicine.jpg"));
        medicine2.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Covid",30,"2021-06-06"),new DiscountEntity("Black fungus",60,"2021-06-06"))));

        ProductEntity medicine3 = new ProductEntity("Insulin",500,medicineShop2.getShopName(),ProductEnum.MEDICINE.toString(),null,null);
        medicine3.setProductImage(getImage("src/main/resources/static/images/medicine.jpg"));
        medicine3.setProductDiscounts(new HashSet<>(Arrays.asList(new DiscountEntity("Covid",20,"2021-06-06"),new DiscountEntity("Yellow fungus",70,"2021-06-06"))));

        ProductEntity medicine4 = new ProductEntity("Smallpox",50,medicineShop3.getShopName(),ProductEnum.MEDICINE.toString(),null,null);
        medicine4.setProductImage(getImage("src/main/resources/static/images/medicine.jpg"));


        medicineShop1.setShopProducts(new HashSet<>(Arrays.asList(medicine1,medicine2)));
        medicineShop2.setShopProducts(new HashSet<>(Collections.singletonList(medicine3)));
        medicineShop3.setShopProducts(new HashSet<>(Collections.singletonList(medicine4)));

//        complete medicine shops ---------------------------------------------------------------

        // restaurant shop ------------------------------------------------------------------
        ShopEntity restaurant1 = new ShopEntity("PizzaHut", getImage("src/main/resources/static/images/brand.jpg"));
        restaurant1.setShopImage(getImage("src/main/resources/static/images/restaurant.jpg"));

        ProductEntity food1 = new ProductEntity("Pizza",500,restaurant1.getShopName(),ProductEnum.RESTAURANT.toString(),null,null);
        food1.setProductImage(getImage("src/main/resources/static/images/restaurant.jpg"));
        food1.setProductDiscounts(new HashSet<>(Collections.singletonList(new DiscountEntity("First time user", 10, "2021-06-06"))));

        restaurant1.setShopProducts(new HashSet<>(Collections.singletonList(food1)));

        // complete restaurant items -----------------------------------------------------------------

//        Admin work-----------------------------------------------------------------
        AdminEntity admin1 = new AdminEntity("zsaifi","zsaifi@deloitte.com",passwordEncoder.encode("123"));
        AdminEntity admin2 = new AdminEntity("praveen","parveen@deloitte.com",passwordEncoder.encode("123"));
        AdminEntity admin3 = new AdminEntity("Shah...","s@deloitte.com",passwordEncoder.encode("123"));

        admin1.setAdminShops(new HashSet<>(Arrays.asList(clothShop1,medicineShop1,restaurant1)));
        admin2.setAdminShops(new HashSet<>(Arrays.asList(clothShop2,medicineShop2)));
        admin3.setAdminShops(new HashSet<>(Arrays.asList(clothShop3,medicineShop3)));


        admin1 = adminRepository.save(admin1);
        admin2 = adminRepository.save(admin2);
        admin3 = adminRepository.save(admin3);

        addRecommendation();
        log.info("Data load successfully");
    }

    void addRecommendation() {
        RecommendEntity recommend1 = new RecommendEntity(ProductEnum.CLOTHS.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend1.setRecommendImage(getImage("src/main/resources/static/images/clothes_offer1.jpg"));
        recommendRepository.save(recommend1);

        RecommendEntity recommend2 = new RecommendEntity(ProductEnum.MEDICINE.toString(), "2021-06-25"," Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend2.setRecommendImage(getImage("src/main/resources/static/images/medicine_offer1.png"));
        recommendRepository.save(recommend2);

        RecommendEntity recommend3 = new RecommendEntity(ProductEnum.RESTAURANT.toString(), "2021-06-25"," Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend3.setRecommendImage(getImage("src/main/resources/static/images/RESTAURANT_offer1.jpg"));
        recommendRepository.save(recommend3);

        RecommendEntity recommend4 = new RecommendEntity(ProductEnum.CLOTHS.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend4.setRecommendImage(getImage("src/main/resources/static/images/cloth1.jpg"));
        recommendRepository.save(recommend4);

        RecommendEntity recommend5 = new RecommendEntity(ProductEnum.CLOTHS.toString(), "2021-06-25"," Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend5.setRecommendImage(getImage("src/main/resources/static/images/cloth2.jpg"));
        recommendRepository.save(recommend5);

        RecommendEntity recommend6 = new RecommendEntity(ProductEnum.RESTAURANT.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend6.setRecommendImage(getImage("src/main/resources/static/images/RESTAURANT1.jpg"));
        recommendRepository.save(recommend6);

        RecommendEntity recommend7 = new RecommendEntity(ProductEnum.RESTAURANT.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend7.setRecommendImage(getImage("src/main/resources/static/images/RESTAURANT2.jpg"));
        recommendRepository.save(recommend7);

        RecommendEntity recommend8 = new RecommendEntity(ProductEnum.MEDICINE.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend8.setRecommendImage(getImage("src/main/resources/static/images/medicine1.jpg"));
        recommendRepository.save(recommend8);

        RecommendEntity recommend9 = new RecommendEntity(ProductEnum.MEDICINE.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend9.setRecommendImage(getImage("src/main/resources/static/images/medicine2.jpg"));
        recommendRepository.save(recommend9);

        RecommendEntity recommend10 = new RecommendEntity(ProductEnum.CLOTHS.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend10.setRecommendImage(getImage("src/main/resources/static/images/cloth3.jpg"));
        recommendRepository.save(recommend10);

        RecommendEntity recommend11 = new RecommendEntity(ProductEnum.RESTAURANT.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend11.setRecommendImage(getImage("src/main/resources/static/images/RESTAURANT4.jpg"));
        recommendRepository.save(recommend11);

        RecommendEntity recommend12 = new RecommendEntity(ProductEnum.RESTAURANT.toString(), "2021-06-25", " Get exciting cash back offers and extra discounts on Men and Women Fashion clothing brands");
        recommend12.setRecommendImage(getImage("src/main/resources/static/images/RESTAURANT3.jpg"));
        recommendRepository.save(recommend12);
    }

    /**
     * This function will convert the image into byte format.
     * @param imageUrl image url
     * @return byte array
     */
    Byte[] getImage(String imageUrl) {
        try{
            // get the image in resources folder
            BufferedImage bImage = ImageIO.read(new File(imageUrl));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);

            // convert into byte array
            byte[] byteData = bos.toByteArray();

            // convert  byte[] into Byte[]
            Byte[] bytesImage = new Byte[byteData.length];

            int i = 0;
            for (byte data : byteData) {
                bytesImage[i++] = data; //Autoboxing
            }
            return bytesImage;
        } catch (Exception e) {

        }
        return null;
    }

}
