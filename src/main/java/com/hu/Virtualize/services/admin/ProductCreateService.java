package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.ShopEntity;

public interface ProductCreateService {
    ShopEntity insertProduct(ProductCommand productCommand);
    ShopEntity updateProduct(ProductCommand productCommand);
    String deleteProduct(ProductCommand productCommand);
}
