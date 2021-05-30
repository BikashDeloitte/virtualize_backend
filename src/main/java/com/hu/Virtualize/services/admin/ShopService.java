package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ShopCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.ShopEntity;

import java.util.Set;

public interface ShopService {
    AdminEntity insertShop(ShopCommand shopCommand);
    AdminEntity updateShop(ShopCommand shopCommand);
    AdminEntity deleteShop(ShopCommand shopCommand);
    Set<ShopEntity> getAllShopsByAdminId(Long id);
}
