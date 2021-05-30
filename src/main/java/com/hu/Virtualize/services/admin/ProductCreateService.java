package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.AdminEntity;
import com.hu.Virtualize.entities.ShopEntity;

import java.util.List;
import java.util.Set;

public interface ProductCreateService {
    ShopEntity insertProduct(ProductCommand productCommand);
    ShopEntity updateProduct(ProductCommand productCommand);
    ShopEntity deleteProduct(ProductCommand productCommand);
//    Set<ShopEntity> getAllShopsByAdminId(Long id);
}
