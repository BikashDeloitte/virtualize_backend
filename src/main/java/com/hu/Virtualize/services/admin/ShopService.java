package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ShopCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.ShopEntity;

public interface ShopService {
    AdminEntity insertShop(ShopCommand shopCommand);
    AdminEntity updateShop(ShopCommand shopCommand);
    String deleteShop(ShopCommand shopCommand);
}
