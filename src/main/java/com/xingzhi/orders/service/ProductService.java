package com.xingzhi.orders.service;

import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.Product;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product);
    boolean updateProductName(Integer id, String name);
    boolean updateProductPrice(Integer id, Double price);
    List<Product> getProducts();
    Product getProductByName(String name);
    Product getProductById(Integer id);
    boolean deleteProductByName(String name);
    boolean deleteProductById(Integer id);
    List<Integer> getProductCategories(Integer productId);
}
