package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ShopCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.ShopEntity;
import com.hu.Virtualize.repositories.AdminRepository;
import com.hu.Virtualize.repositories.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ShopRepository shopRepository;

    /**
     * This function will add new shop in admin list.
     *
     * @param shopCommand shop details.
     * @return admin details.
     */
    @Transactional
    public AdminEntity insertShop(ShopCommand shopCommand) {
        ShopEntity shopEntity = new ShopEntity(shopCommand.getShopName());

        // if location is given by admin
        if (shopCommand.getShopLocation() != null) {
            shopEntity.setShopLocation(shopCommand.getShopLocation());
        }

        AdminEntity admin = adminRepository.findByAdminId(shopCommand.getAdminId());

        if (admin == null) {
            log.error("Invalid Admin ");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid admin");
        }

        admin.getAdminShops().add(shopEntity);

        admin = adminRepository.save(admin);

        return admin;
    }

    /**
     * This function will update the shop details in admin list.
     *
     * @param shopCommand shop and admin details.
     * @return admin details.
     */
    @Transactional
    public AdminEntity updateShop(ShopCommand shopCommand) {

        AdminEntity admin = adminRepository.findByAdminId(shopCommand.getAdminId());

        if (admin == null) {
            log.error("Invalid Admin ");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid admin");
        }

        boolean presentShop = false;
        ShopEntity adminUpdateShop = null;

        for (ShopEntity shop : admin.getAdminShops()) {
            if (shop.getShopId().equals(shopCommand.getShopId())) {
                presentShop = true;

                // if shop name will change
                if (shopCommand.getShopName() != null) {
                    shop.setShopName(shopCommand.getShopName());
                }

                // if shop location will change
                if (shopCommand.getShopLocation() != null) {
                    shop.setShopLocation(shopCommand.getShopLocation());
                }
                adminUpdateShop = shop;
                break;
            }
        }

        if (!presentShop) {
            log.error("This shop doesn't comes under given admin");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This shop doesn't comes under given admin");
        }

        // update shop value
        admin.getAdminShops().add(adminUpdateShop);
        // update information
        admin = adminRepository.save(admin);
        return admin;
    }

    /**
     * This function will delete the shop in admin list and all shop product.
     *
     * @param shopCommand shop or admin details.
     * @return status
     */
    public AdminEntity deleteShop(ShopCommand shopCommand) {
        AdminEntity admin = adminRepository.findByAdminId(shopCommand.getAdminId());

        if (admin == null) {
            log.error("Invalid Admin ");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid admin");
        }

        Set<ShopEntity> adminShops = new HashSet<>();

        // check shop is valid or not
        boolean presentShop = false;
        for (ShopEntity shop : admin.getAdminShops()) {
            if (shop.getShopId().equals(shopCommand.getShopId())) {
                presentShop = true;
            } else {
                adminShops.add(shop);
            }
        }

        if (!presentShop) {
            log.error("This shop doesn't comes under given admin");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This shop doesn't comes under given admin");
        }

        admin.setAdminShops(adminShops);
        admin = adminRepository.save(admin);

        // delete shop and all its product and with all product discount
        shopRepository.deleteById(shopCommand.getShopId());

        return admin;
    }

        public Set<ShopEntity> getAllShopsByAdminId(Long id) {

        Optional<AdminEntity> admin = adminRepository.findById(id);
        if (admin == null) {
            log.error("Invalid Admin ");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid admin");
        }

        Set<ShopEntity> shops = null;
        List<AdminEntity> allAdmin = adminRepository.findAll();
        for (int i = 0; i < allAdmin.size(); i++) {
            if (allAdmin.get(i).getAdminId().equals(id)) {
                shops= allAdmin.get(i).getAdminShops();
            }
        }
        return shops;
    }
}
