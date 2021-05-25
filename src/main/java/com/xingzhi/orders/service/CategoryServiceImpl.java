package com.xingzhi.orders.service;

import com.xingzhi.orders.JDBC.*;
import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CategoryServiceImpl implements CategoryService{
    private CategoryDao categoryDao;
    private ClassifiedProductDao classifiedProductDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, ClassifiedProductDao classifiedProductDao){
        this.categoryDao = categoryDao;
        this.classifiedProductDao = classifiedProductDao;
    }
    @Override
    public boolean addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public boolean updateCategoryName(Integer id, String name) {
        return categoryDao.updateCategoryName(id,name);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public boolean deleteCategoryByName(String name) {
        return categoryDao.deleteCategoryByName(name);
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        return categoryDao.deleteCategoryById(id);
    }

    @Override
    public List<Integer> getCategoryProducts(Integer categoryId) {
        return classifiedProductDao.getCategoryProducts(categoryId);
    }
}
