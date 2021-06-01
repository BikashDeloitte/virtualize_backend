package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.AdminEntity;

public interface ProductCreateService {
    AdminEntity insertProduct(ProductCommand productCommand);
    AdminEntity updateProduct(ProductCommand productCommand);
    AdminEntity deleteProduct(ProductCommand productCommand);
}
