package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.Product;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{
    private Logger logger;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/coding_challenge";
    private static final String USER="admin";
    private static final String PASS="66545321";

    @Autowired
    public ProductDaoImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "insert into product (id,name,price) values(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setDouble(3,product.getPrice());
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
        logger.info("exit the method addProduct");
        return isSuccessful;
    }

    @Override
    public boolean updateProductName(Integer id, String name) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update product set name=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, id);
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
        logger.info("exit the method updateProductName");
        return isSuccessful;
    }

    @Override
    public boolean updateProductPrice(Integer id, Double price) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update product set price=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, price);
            stmt.setInt(2, id);
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
        logger.info("exit the method updateProductPrice");
        return isSuccessful;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM product";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                double price = rs.getDouble("price");
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                products.add(product);
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
        logger.info("exit the method getProduct");
        return products;
    }

    @Override
    public Product getProductByName(String name) {
        Product product = new Product();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from product where name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                int id=rs.getInt("id");
                double price = rs.getDouble("price");
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
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
        logger.info("exit the method getProductByName");
        return product;
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = new Product();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from product where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                String name=rs.getString("name");
                double price = rs.getDouble("price");
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
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
        logger.info("exit the method getProductById");
        return product;
    }

    @Override
    public boolean deleteProductByName(String name) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from product where name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
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
        logger.info("exit the method deleteProductByName");
        return isSuccessful;
    }

    @Override
    public boolean deleteProductById(Integer id) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from product where id=?";
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
        logger.info("exit the method deleteProductById");
        return isSuccessful;
    }
}

