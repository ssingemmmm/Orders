package com.xingzhi.orders.service;

import com.xingzhi.orders.model.Orders;
import com.xingzhi.orders.model.Product;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface OrdersService {
    boolean addOrder(Orders orders);
    boolean updateOrderStatues(Integer id, String statues);
    List<Orders> getOrders();
    List<Orders> getOrdersByCustomerId(Integer customerId);
    List<Orders> getOrdersByDate(Date date);
    List<Orders> getOrdersByStatues(String statues);
    boolean deleteOrderByCustomerId(Integer customerId);
    boolean deleteOrderById(Integer id);
    Map<Integer,Double> getOrderedProductsInDateRange(Date startDate, Date endDate);
}
