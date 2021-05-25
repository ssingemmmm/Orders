package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Orders;
import com.xingzhi.orders.model.Product;

import java.sql.Date;
import java.util.List;

public interface OrdersDao {
    boolean addOrder(Orders orders);
    boolean updateOrderStatues(Integer id, String statues);
    List<Orders> getOrders();
    List<Orders> getOrdersByCustomerId(Integer customerId);
    List<Orders> getOrdersByDate(Date date);
    List<Orders> getOrdersByStatues(String statues);
    List<Orders> getOrdersByDateRange(Date startDate, Date endDate);
    boolean deleteOrderByCustomerId(Integer customerId);
    boolean deleteOrderById(Integer id);

}
