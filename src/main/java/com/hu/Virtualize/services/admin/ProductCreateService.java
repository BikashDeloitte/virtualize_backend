package com.hu.Virtualize.services.admin;

import com.hu.Virtualize.commands.admin.ProductCommand;
import com.hu.Virtualize.entities.AdminEntity;
import java.util.List;

public interface ProductCreateService {
    AdminEntity insertProduct(ProductCommand productCommand);
    AdminEntity updateProduct(ProductCommand productCommand);
    AdminEntity deleteProduct(ProductCommand productCommand);
    List<String> getAllProductType();
}
