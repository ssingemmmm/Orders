package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.Customer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private Logger logger;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/coding_challenge";
    private static final String USER="admin";
    private static final String PASS="66545321";

    @Autowired
    public CustomerDaoImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "insert into customer (id,name) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getName());
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
        logger.info("exit the method addCustomer");
        return isSuccessful;
    }

    @Override
    public boolean updateCustomerName(Integer id, String name) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update customer set name=? where id=?";
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
        logger.info("exit the method updateCustomerName");
        return isSuccessful;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM customer";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customers.add(customer);
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
        logger.info("exit the method getCustomer");
        return customers;
    }

    @Override
    public Customer getCustomerByName(String name) {
        Customer customer = new Customer();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from customer where name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                int id=rs.getInt("id");
                customer.setId(id);
                customer.setName(name);
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
        logger.info("exit the method getCustomerByName");
        return customer;
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Customer customer = new Customer();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from customer where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                String name=rs.getString("name");
                customer.setId(id);
                customer.setName(name);
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
        logger.info("exit the method getCustomerById");
        return customer;
    }

    @Override
    public boolean deleteCustomerByName(String name) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from customer where name=?";
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
        logger.info("exit the method deleteCustomerByName");
        return isSuccessful;
    }

    @Override
    public boolean deleteCustomerById(Integer id) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from customer where id=?";
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
        logger.info("exit the method deleteCustomerById");
        return isSuccessful;
    }
}
