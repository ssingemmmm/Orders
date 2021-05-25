package com.xingzhi.orders.service;

import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.Product;

import java.util.List;

public interface CategoryService {
    boolean addCategory(Category category);
    boolean updateCategoryName(Integer id, String name);
    List<Category> getCategories();
    Category getCategoryByName(String name);
    Category getCategoryById(Integer id);
    boolean deleteCategoryByName(String name);
    boolean deleteCategoryById(Integer id);
    List<Integer> getCategoryProducts(Integer categoryId);
}
