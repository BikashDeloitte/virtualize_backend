package com.hu.Virtualize.services.user;

import java.util.List;

public interface ProductCategoryService {
    List<String> getStoreName(String productType);
    List<String> getProductNames(String productType);
}