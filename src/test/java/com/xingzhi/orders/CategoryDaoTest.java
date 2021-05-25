package com.xingzhi.orders;

import com.xingzhi.orders.JDBC.CategoryDao;
import com.xingzhi.orders.init.AppInitializer;
import com.xingzhi.orders.model.Category;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class CategoryDaoTest {
    @Autowired
    private CategoryDao categoryDao;
    private Category testCategory;
    private String testCategoryName;
    @Before
    public void init() {
        testCategory = new Category();
        testCategoryName = "test";
        testCategory.setName(testCategoryName);
        categoryDao.addCategory(testCategory);
    }

    @After
    public void tearDown(){
        categoryDao.deleteCategoryByName(testCategoryName);
    }

    @Test
    public void getCategories(){
        List<Category> categories = categoryDao.getCategories();
        Assert.assertEquals(categories.size(),5);
    }

    @Test
    public void getCategoryByName(){
        Category category = categoryDao.getCategoryByName(testCategory.getName());
        Assert.assertNotNull(category);
    }

    @Test
    public void getCategoryById(){
        Category category = categoryDao.getCategoryById(0);
        Assert.assertEquals(category.getName(),testCategoryName);
    }

    @Test
    public void updateCategoryName(){
        Category category = categoryDao.getCategoryByName(testCategory.getName());
        categoryDao.updateCategoryName(category.getId(),"test2");
        testCategoryName = "test2";
        Assert.assertEquals(categoryDao.getCategoryById(category.getId()).getName(),"test2");

    }
}
