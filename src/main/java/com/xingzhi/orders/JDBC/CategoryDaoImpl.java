package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Category;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class CategoryDaoImpl implements CategoryDao{
    private Logger logger;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/coding_challenge";
    private static final String USER="admin";
    private static final String PASS="66545321";

    @Autowired
    public CategoryDaoImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean addCategory(Category category) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "insert into category (id,name) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, category.getId());
            stmt.setString(2, category.getName());
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
        logger.info("exit the method addCategory");
        return isSuccessful;
    }

    @Override
    public boolean updateCategoryName(Integer id, String name) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "update category set name=? where id=?";
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
        logger.info("exit the method updateCategoryName");
        return isSuccessful;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM category";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                Category category = new Category();
                category.setId(id);
                category.setName(name);
                categories.add(category);
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
        logger.info("exit the method getCategory");
        return categories;
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = new Category();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from category where name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                int id=rs.getInt("id");
                category.setId(id);
                category.setName(name);
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
        logger.info("exit the method getCategoryByName");
        return category;
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category = new Category();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from category where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            //step 4: extract data from result set
            while (rs.next()) {
                String name=rs.getString("name");
                category.setId(id);
                category.setName(name);
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
        logger.info("exit the method getCategoryById");
        return category;
    }

    @Override
    public boolean deleteCategoryByName(String name) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from category where name=?";
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
        logger.info("exit the method deleteCategoryByName");
        return isSuccessful;
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        boolean isSuccessful = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from category where id=?";
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
        logger.info("exit the method deleteCategoryById");
        return isSuccessful;
    }
}
