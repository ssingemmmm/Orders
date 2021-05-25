package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.ClassifiedProduct;
import com.xingzhi.orders.model.Product;
import java.util.*;

public interface ClassifiedProductDao {
    boolean addClassifiedProduct(ClassifiedProduct classifiedProduct);
    List<Integer> getProductCategories(Integer productId);
    List<Integer> getCategoryProducts(Integer categoryId);
    List<ClassifiedProduct> getClassifiedProduct();
    ClassifiedProduct getClassifiedProductById(Integer id);
    ClassifiedProduct getClassifiedProductByProductIdCategoryId(Integer productId, Integer categoryId);
    boolean updateClassifiedProduct(Integer id, Integer productId, Integer categoryId);
    boolean deleteClassifiedProduct(Integer id);
}
