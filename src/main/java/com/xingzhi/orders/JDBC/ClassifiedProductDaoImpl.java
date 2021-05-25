package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.ClassifiedProduct;
import com.xingzhi.orders.model.Product;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassifiedProductDaoImpl implements ClassifiedProductDao{
    private Logger logger;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/coding_challenge";
    private static final String USER="admin";
    private static final String PASS="66545321";

    @Autowired
    public ClassifiedProductDaoImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean addClassifiedProduct(ClassifiedProduct classifiedProduct) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "insert into classifiedProduct (id,product_id,category_id) values(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, classifiedProduct.getId());
            stmt.setInt(2, classifiedProduct.getProductId());
            stmt.setInt(3, classifiedProduct.getCategoryId());
            stmt.execute();
            isSuccessful = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method addClassifiedProduct");
        return isSuccessful;
    }

    @Override
    public List<Integer> getProductCategories(Integer productId) {
        List<Integer> categoryIds = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from classifiedProduct where product_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                categoryIds.add(categoryId);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } finally {
            //close resources
            try{
                if(rs != null)rs.close();
                if(stmt !=null) stmt.close();
                if(conn!= null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        logger.info("exit the method getProductCategories");
        return categoryIds;
    }

    @Override
    public List<Integer> getCategoryProducts(Integer categoryId) {
        List<Integer> productIds = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from classifiedProduct where category_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                productIds.add(productId);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } finally {
            //close resources
            try{
                if(rs != null)rs.close();
                if(stmt !=null) stmt.close();
                if(conn!= null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        logger.info("exit the method getCategoryProducts");
        return productIds;
    }

    @Override
    public List<ClassifiedProduct> getClassifiedProduct() {
        List<ClassifiedProduct> classifiedProducts = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM classifiedProduct";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("id");
                int productId=rs.getInt("product_id");
                int categoryId=rs.getInt("category_id");
                ClassifiedProduct classifiedProduct = new ClassifiedProduct();
                classifiedProduct.setId(id);
                classifiedProduct.setProductId(productId);
                classifiedProduct.setCategoryId(categoryId);
                classifiedProducts.add(classifiedProduct);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } finally {
            //close resources
            try{
                if(rs != null)rs.close();
                if(stmt !=null) stmt.close();
                if(conn!= null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        logger.info("exit the method getClassifiedProduct");
        return classifiedProducts;
    }

    @Override
    public ClassifiedProduct getClassifiedProductById(Integer id) {
        ClassifiedProduct classifiedProduct = new ClassifiedProduct();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from classifiedProduct where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int categoryId = rs.getInt("category_id");
                classifiedProduct.setId(id);
                classifiedProduct.setProductId(productId);
                classifiedProduct.setCategoryId(categoryId);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method getClassifiedProductById");
        return classifiedProduct;
    }

    @Override
    public ClassifiedProduct getClassifiedProductByProductIdCategoryId(Integer productId, Integer categoryId) {
        ClassifiedProduct classifiedProduct = new ClassifiedProduct();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from classifiedProduct where product_id=? and category_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            stmt.setInt(2, categoryId);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                classifiedProduct.setId(id);
                classifiedProduct.setProductId(productId);
                classifiedProduct.setCategoryId(categoryId);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method getClassifiedProductByProductIdCategoryId");
        return classifiedProduct;
    }

    @Override
    public boolean updateClassifiedProduct(Integer id, Integer productId, Integer categoryId) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update classfiedProduct set product_id=?, category_id=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            stmt.setInt(2, categoryId);
            stmt.setInt(3, id);
            stmt.execute();
            isSuccessful = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method updateClassifiedProduct");
        return isSuccessful;
    }

    @Override
    public boolean deleteClassifiedProduct(Integer id) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from classifiedProduct where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            isSuccessful = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("exit the method deleteClassifiedProduct");
        return isSuccessful;
    }
}
