package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.OrderedProduct;
import com.xingzhi.orders.model.Orders;
import com.xingzhi.orders.model.Product;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderedProductDaoImpl implements OrderedProductDao{
    private Logger logger;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/coding_challenge";
    private static final String USER="admin";
    private static final String PASS="66545321";

    @Autowired
    public OrderedProductDaoImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean addOrderedProduct(OrderedProduct orderedProduct) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "insert into orderedProduct (id,order_id,product_id,quantity) values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderedProduct.getId());
            stmt.setInt(2, orderedProduct.getOrderId());
            stmt.setInt(3, orderedProduct.getProductId());
            stmt.setDouble(4,orderedProduct.getQuantity());
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
        logger.info("exit the method addOrderedProduct");
        return isSuccessful;
    }

    @Override
    public boolean updateOrderedProductId(Integer id, Integer productId) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update orderedProduct set product_id=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
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
        logger.info("exit the method updateOrderedProductId");
        return isSuccessful;
    }

    @Override
    public boolean updateOrderedProductQuantity(Integer id, Double quantity) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update orderedProduct set quantity=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, quantity);
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
        logger.info("exit the method updateOrderedProductQuantity");
        return isSuccessful;
    }

    @Override
    public List<OrderedProduct> getOrderedProductsByOrderId(Integer orderId) {
        List<OrderedProduct> orderedProducts = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from orderedProduct where order_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                int productId = rs.getInt("product_id");
                double quantity = rs.getDouble("quantity");

                OrderedProduct orderedProduct = new OrderedProduct();
                orderedProduct.setId(id);
                orderedProduct.setOrderId(orderId);
                orderedProduct.setProductId(productId);
                orderedProduct.setQuantity(quantity);
                orderedProducts.add(orderedProduct);
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
        logger.info("exit the method getOrderedProductsByOrderId");
        return orderedProducts;
    }

    @Override
    public List<OrderedProduct> getOrderedProduct() {
        List<OrderedProduct> orderedProducts = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM orderedProduct";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("id");
                int orderId = rs.getInt("order_id");
                int productId = rs.getInt("product_id");
                double quantity = rs.getDouble("quantity");
                OrderedProduct orderedProduct = new OrderedProduct();
                orderedProduct.setId(id);
                orderedProduct.setOrderId(orderId);
                orderedProduct.setProductId(productId);
                orderedProduct.setQuantity(quantity);
                orderedProducts.add(orderedProduct);
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
        logger.info("exit the method getOrderedProduct");
        return orderedProducts;
    }

    @Override
    public boolean deleteOrderedProductByOrderId(Integer orderId) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from orderedProduct where order_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
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
        logger.info("exit the method deleteOrderedProductByOrderId");
        return isSuccessful;
    }

    @Override
    public boolean deleteOrderedProductById(Integer id) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from orderedProduct where id=?";
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
        logger.info("exit the method deleteOrderedProductById");
        return isSuccessful;
    }
}
