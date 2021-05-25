package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Category;
import java.util.List;

public interface CategoryDao {
    boolean addCategory(Category category);
    boolean updateCategoryName(Integer id, String name);
    List<Category> getCategories();
    Category getCategoryByName(String name);
    Category getCategoryById(Integer id);
    boolean deleteCategoryByName(String name);
    boolean deleteCategoryById(Integer id);
}
