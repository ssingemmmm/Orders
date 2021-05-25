package com.xingzhi.orders.service;

import com.xingzhi.orders.JDBC.CategoryDao;
import com.xingzhi.orders.JDBC.ClassifiedProductDao;
import com.xingzhi.orders.JDBC.ProductDao;
import com.xingzhi.orders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private ClassifiedProductDao classifiedProductDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ClassifiedProductDao classifiedProductDao){
        this.productDao = productDao;
        this.classifiedProductDao = classifiedProductDao;
    }

    @Override
    public boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public boolean updateProductName(Integer id, String name) {
        return productDao.updateProductName(id,name);
    }

    @Override
    public boolean updateProductPrice(Integer id, Double price) {
        return productDao.updateProductPrice(id,price);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public Product getProductByName(String name) {
        return productDao.getProductByName(name);
    }

    @Override
    public Product getProductById(Integer id) {
        return getProductById(id);
    }

    @Override
    public boolean deleteProductByName(String name) {
        return deleteProductByName(name);
    }

    @Override
    public boolean deleteProductById(Integer id) {
        return deleteProductById(id);
    }

    @Override
    public List<Integer> getProductCategories(Integer productId) {
        return classifiedProductDao.getProductCategories(productId);
    }
}
