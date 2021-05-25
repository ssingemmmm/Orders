package com.xingzhi.orders;

import com.xingzhi.orders.JDBC.ProductDao;
import com.xingzhi.orders.init.AppInitializer;
import com.xingzhi.orders.model.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;
    private Product testProduct;
    private String testProductName;
    @Before
    public void init() {
        testProduct = new Product();
        testProductName = "test";
        testProduct.setName(testProductName);
        productDao.addProduct(testProduct);
    }

    @After
    public void tearDown(){
        productDao.deleteProductByName(testProductName);
    }

    @Test
    public void getProducts(){
        List<Product> products = productDao.getProducts();
        Assert.assertEquals(products.size(),11);
    }

    @Test
    public void getProductByName(){
        Product product = productDao.getProductByName(testProduct.getName());
        Assert.assertNotNull(product);
    }

    @Test
    public void getProductById(){
        Product product = productDao.getProductById(0);
        Assert.assertEquals(product.getName(),testProductName);
    }

    @Test
    public void updateProductName(){
        Product product = productDao.getProductByName(testProduct.getName());
        productDao.updateProductName(product.getId(),"test2");
        testProductName = "test2";
        Assert.assertEquals(productDao.getProductById(product.getId()).getName(),"test2");
    }

    @Test
    public void updateProductPrice(){
        Product product = productDao.getProductByName(testProduct.getName());
        productDao.updateProductPrice(product.getId(), 111.1);
        System.out.println(product.getPrice());
        Assert.assertEquals(productDao.getProductById(product.getId()).getPrice(),111.1,1);
    }
}

