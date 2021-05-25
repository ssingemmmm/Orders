package com.xingzhi.orders.service;

import com.xingzhi.orders.JDBC.ClassifiedProductDao;
import com.xingzhi.orders.JDBC.OrderedProductDao;
import com.xingzhi.orders.JDBC.OrdersDao;
import com.xingzhi.orders.JDBC.ProductDao;
import com.xingzhi.orders.model.OrderedProduct;
import com.xingzhi.orders.model.Orders;
import com.xingzhi.orders.model.Product;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrdersServiceImpl implements OrdersService{
    private OrdersDao ordersDao;
    private OrderedProductDao orderedProductDao;

    @Autowired
    public OrdersServiceImpl(OrdersDao ordersDao, OrderedProductDao orderedProductDao){
        this.ordersDao = ordersDao;
        this.orderedProductDao = orderedProductDao;
    }

    @Override
    public boolean addOrder(Orders orders) {
        return ordersDao.addOrder(orders);
    }

    @Override
    public boolean updateOrderStatues(Integer id, String statues) {
        return ordersDao.updateOrderStatues(id,statues);
    }

    @Override
    public List<Orders> getOrders() {
        return ordersDao.getOrders();
    }

    @Override
    public List<Orders> getOrdersByCustomerId(Integer customerId) {
        return ordersDao.getOrdersByCustomerId(customerId);
    }

    @Override
    public List<Orders> getOrdersByDate(Date date) {
        return ordersDao.getOrdersByDate(date);
    }

    @Override
    public List<Orders> getOrdersByStatues(String statues) {
        return ordersDao.getOrdersByStatues(statues);
    }

    @Override
    public boolean deleteOrderByCustomerId(Integer customerId) {
        return ordersDao.deleteOrderByCustomerId(customerId);
    }

    @Override
    public boolean deleteOrderById(Integer id) {
        return ordersDao.deleteOrderById(id);
    }

    @Override
    public Map<Integer, Double> getOrderedProductsInDateRange(Date startDate, Date endDate) {
        List<Orders> orders = ordersDao.getOrdersByDateRange(startDate,endDate);
        List<OrderedProduct> allProducts = new ArrayList<>();
        Map<Integer,Double> hm = new HashMap<>();
        for(Orders o: orders){
            List<OrderedProduct> products = orderedProductDao.getOrderedProductsByOrderId(o.getId());
            allProducts.addAll(products);
        }
        for(OrderedProduct o: allProducts){
            hm.put(o.getProductId(),hm.getOrDefault(o.getProductId(),0.0)+1);
        }
        return hm;
    }
}
