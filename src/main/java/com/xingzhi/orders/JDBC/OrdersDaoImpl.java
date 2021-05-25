package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Orders;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao{

    private Logger logger;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/coding_challenge";
    private static final String USER="admin";
    private static final String PASS="66545321";

    @Autowired
    public OrdersDaoImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean addOrder(Orders orders) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "insert into orders (customer_id,statues,date) values(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orders.getCustomerId());
            stmt.setString(2,orders.getStatues());
            stmt.setDate(3,orders.getDate());
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
        logger.info("exit the method addOrder");
        return isSuccessful;
    }

    @Override
    public boolean updateOrderStatues(Integer id, String statues) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update orders set statues=? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, statues);
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
        logger.info("exit the method updateOrderStatues");
        return isSuccessful;
    }

    @Override
    public List<Orders> getOrders() {
        List<Orders> allOrders = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM orders";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("id");
                int customerId=rs.getInt("customer_id");
                String statues = rs.getString("statues");
                Date date = rs.getDate("date");
                Orders order = new Orders();
                order.setId(id);
                order.setCustomerId(customerId);
                order.setStatues(statues);
                order.setDate(date);
                allOrders.add(order);
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
        logger.info("exit the method getOrders");
        return allOrders;
    }

    @Override
    public List<Orders> getOrdersByCustomerId(Integer customerId) {
        List<Orders> allOrders = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from orders where customer_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                String statues = rs.getString("statues");
                Date date = rs.getDate("date");
                Orders order = new Orders();
                order.setId(id);
                order.setCustomerId(customerId);
                order.setStatues(statues);
                order.setDate(date);
                allOrders.add(order);
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
        logger.info("exit the method getOrdersByCustomerId");
        return allOrders;
    }

    @Override
    public List<Orders> getOrdersByDate(Date date) {
        List<Orders> allOrders = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from orders where date=?";
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, date);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                int customerId=rs.getInt("customer_id");
                String statues = rs.getString("statues");
                Orders order = new Orders();
                order.setId(id);
                order.setCustomerId(customerId);
                order.setStatues(statues);
                order.setDate(date);
                allOrders.add(order);
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
        logger.info("exit the method getOrdersByDate");
        return allOrders;
    }

    @Override
    public List<Orders> getOrdersByStatues(String statues) {
        List<Orders> allOrders = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from orders where statues=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, statues);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                int customerId=rs.getInt("customer_id");
                Date date = rs.getDate("date");
                Orders order = new Orders();
                order.setId(id);
                order.setCustomerId(customerId);
                order.setStatues(statues);
                order.setDate(date);
                allOrders.add(order);
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
        logger.info("exit the method getOrdersByStatues");
        return allOrders;
    }

    @Override
    public List<Orders> getOrdersByDateRange(Date startDate, Date endDate) {
        List<Orders> allOrders = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from orders where \n" +
                    "date-? <= ?-? \n" +
                    "and date-?>=0";
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, startDate);
            stmt.setDate(2,endDate);
            stmt.setDate(3,startDate);
            stmt.setDate(4,startDate);
            rs=stmt.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                int customerId=rs.getInt("customer_id");
                Date date = rs.getDate("date");
                String statues = rs.getString("statues");
                Orders order = new Orders();
                order.setId(id);
                order.setCustomerId(customerId);
                order.setStatues(statues);
                order.setDate(date);
                allOrders.add(order);
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
        logger.info("exit the method getOrdersByStatues");
        return allOrders;
    }

    @Override
    public boolean deleteOrderByCustomerId(Integer customerId) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from orders where customer_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
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
        logger.info("exit the method deleteOrderByCustomerId");
        return isSuccessful;
    }

    @Override
    public boolean deleteOrderById(Integer id) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from orders where id=?";
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
        logger.info("exit the method deleteOrderById");
        return isSuccessful;
    }
}
