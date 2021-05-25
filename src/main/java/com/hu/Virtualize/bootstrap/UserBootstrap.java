package com.hu.Virtualize.bootstrap;

import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.DiscountEntity;
import com.hu.Virtualize.entities.ProductEntity;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.entities.UserEntity;
import com.hu.Virtualize.entities.UserInterestEntity;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Slf4j
@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private AdminRepository adminRepository;

    public UserBootstrap(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Start user bootstrap class");
        testDataBase();
    }

    public void testDataBase() {
        // add user1 in database
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserName("Zhatab Saifi");
        userEntity1.setUserEmail("zhatabsaifi@gmail.com");
        userEntity1.setUserPassword("123");

        // user interest
        UserInterestEntity userInterestEntity1 = new UserInterestEntity("LP");
        UserInterestEntity userInterestEntity2 = new UserInterestEntity("Peter England");

        userEntity1.getUserInterestEntities().add(userInterestEntity1);
        userEntity1.getUserInterestEntities().add(userInterestEntity2);
        
        // add user into 
        userEntity1 = userRepository.save(userEntity1);
        log.info(userEntity1.toString());


        // add user2 in database
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserName("Bikaw Kuswaha");
        userEntity2.setUserEmail("bkuswaha@gmail.com");
        userEntity2.setUserPassword("123");

        // user interest
        UserInterestEntity userInterestEntity21 = new UserInterestEntity("Allen Solly");
        UserInterestEntity userInterestEntity22 = new UserInterestEntity("Raymond");
        UserInterestEntity userInterestEntity23 = new UserInterestEntity("FabIndia");

        userEntity2.getUserInterestEntities().add(userInterestEntity21);
        userEntity2.getUserInterestEntities().add(userInterestEntity22);
        userEntity2.getUserInterestEntities().add(userInterestEntity23);

        // add user 2 into
        userEntity2 = userRepository.save(userEntity2);
        log.info(userEntity2.toString());

// ---------------------------------------------------------------------------------------------------------
        // create admin
        AdminEntity admin1 = new AdminEntity("zs","zsaifi@deloitte.com","123");
        AdminEntity admin2 = new AdminEntity("bk","parveen@deloitte.com","123");

        // create shops
        ShopEntity shop1 = new ShopEntity("LP");
        ShopEntity shop2 = new ShopEntity("Peter England");
        ShopEntity shop3 = new ShopEntity("Raymond");
        ShopEntity shop4 = new ShopEntity("FabIndia");

        // create product
        ProductEntity product1 = new ProductEntity("Jeans",1200,"Male","M");
        ProductEntity product2 = new ProductEntity("Jeans",1200,"Unisex","L");
        ProductEntity product3 = new ProductEntity("Shirt",200,"Male","S");
        ProductEntity product4 = new ProductEntity("Shirt",400,"Female","L");
        ProductEntity product5 = new ProductEntity("T-shirt",800,"Unisex","M");
        ProductEntity product6 = new ProductEntity("T-shirt",800,"Female","L");

        ProductEntity product7 = new ProductEntity("Tie",1200,"Male","M");
        ProductEntity product8 = new ProductEntity("Lower",1200,"Unisex","L");
        ProductEntity product9 = new ProductEntity("Tie",200,"Male","S");
        ProductEntity product10 = new ProductEntity("Trouser",400,"Female","L");
        ProductEntity product11 = new ProductEntity("Trouser",800,"Unisex","M");
        ProductEntity product12 = new ProductEntity("T-shirt",800,"Female","L");

        // create discount
        DiscountEntity discount1 = new DiscountEntity("Diwali offer", 5);
        DiscountEntity discount2 = new DiscountEntity("Holi offer", 1);
        DiscountEntity discount3 = new DiscountEntity("Eid offer", 10);
        DiscountEntity discount4 = new DiscountEntity("Last offer", 10);
        DiscountEntity discount5 = new DiscountEntity("First time offer", 50);
        DiscountEntity discount6 = new DiscountEntity("New year offer", 20);
        DiscountEntity discount7 = new DiscountEntity("Abc offer", 7);
        DiscountEntity discount8 = new DiscountEntity("Bcd offer", 20);
        DiscountEntity discount9 = new DiscountEntity("Def offer", 9);

        // add discount on products
        product1.setProductDiscounts(new HashSet<>(Arrays.asList(discount1,discount2)));
        product2.setProductDiscounts(new HashSet<>(Arrays.asList(discount3,discount4)));
        product3.setProductDiscounts(new HashSet<>(Arrays.asList(discount5,discount6)));
        product4.setProductDiscounts(new HashSet<>(Arrays.asList(discount7,discount8)));
        product5.setProductDiscounts(new HashSet<>(Arrays.asList(discount9)));

        // add products in shops
        shop1.setShopProducts(new HashSet<>(Arrays.asList(product1,product2,product7,product8)));
        product1.setBrandName(shop1.getShopName());
        product2.setBrandName(shop1.getShopName());
        product7.setBrandName(shop1.getShopName());
        product8.setBrandName(shop1.getShopName());

        shop2.setShopProducts(new HashSet<>(Arrays.asList(product3,product4,product9,product10)));
        product3.setBrandName(shop2.getShopName());
        product4.setBrandName(shop2.getShopName());
        product9.setBrandName(shop2.getShopName());
        product10.setBrandName(shop2.getShopName());

        shop3.setShopProducts(new HashSet<>(Arrays.asList(product5,product11)));
        product5.setBrandName(shop3.getShopName());
        product11.setBrandName(shop3.getShopName());

        shop4.setShopProducts(new HashSet<>(Arrays.asList(product6,product12)));
        product6.setBrandName(shop4.getShopName());
        product12.setBrandName(shop4.getShopName());


        // add shop in admin list
        admin1.setAdminShops(new HashSet<>(Arrays.asList(shop1,shop2)));
        admin2.setAdminShops(new HashSet<>(Arrays.asList(shop3,shop4)));

        admin1 = adminRepository.save(admin1);
        log.info(admin1.toString());

        admin2 = adminRepository.save(admin2);
        log.info(admin2.toString());

        // add product in user fav list
        userEntity1.setFavouriteProducts(new HashSet<>(Arrays.asList(product1,product2)));
        userEntity2.setFavouriteProducts(new HashSet<>(Arrays.asList(product3,product4, product5)));

        userEntity1 = userRepository.save(userEntity1);
        log.info(userEntity1.toString());


        Optional<AdminEntity> owner = adminRepository.findById(admin1.getAdminId());
        if(owner.isEmpty()) {
            log.info("empty owner " + admin1.getAdminId());
        } else{
            log.info(owner.get().toString());
        }

        log.info(userRepository.findById(userEntity1.getUserId()).get().toString());
        log.info("Complete data uploading in database");
    }
}
