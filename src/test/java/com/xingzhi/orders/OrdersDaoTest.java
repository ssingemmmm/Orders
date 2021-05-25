package com.xingzhi.orders;

import com.xingzhi.orders.JDBC.OrdersDao;
import com.xingzhi.orders.JDBC.ProductDao;
import com.xingzhi.orders.init.AppInitializer;
import com.xingzhi.orders.model.Orders;
import com.xingzhi.orders.model.Product;
import org.hibernate.criterion.Order;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class OrdersDaoTest {
    @Autowired
    private OrdersDao ordersDao;
    private Orders testOrder;
    private String testOrderStatues;
    private Date testOrderDate;
    @Before
    public void init() {
        testOrder = new Orders();
        testOrderStatues = "ready";
        testOrderDate = Date.valueOf("2111-1-1");
        testOrder.setCustomerId(3);
        testOrder.setDate(testOrderDate);
        testOrder.setStatues(testOrderStatues);
        ordersDao.addOrder(testOrder);
    }

    @After
    public void tearDown(){
        ordersDao.deleteOrderById(ordersDao.getOrdersByCustomerId(3).get(0).getId());
    }

    @Test
    public void getOrders(){
        List<Orders> orders = ordersDao.getOrders();
        Assert.assertEquals(orders.size(),4);
    }

    @Test
    public void getOrderByCustomerId(){
        List<Orders> orders = ordersDao.getOrdersByCustomerId(testOrder.getCustomerId());
        Assert.assertEquals(orders.size(),1);
    }

    @Test
    public void getOrdersByDate(){
        List<Orders> orders = ordersDao.getOrdersByDate(testOrderDate);
        Assert.assertEquals(orders.size(),1);
    }

    @Test
    public void getOrdersByStatues(){
        List<Orders> orders = ordersDao.getOrdersByStatues(testOrderStatues);
        Assert.assertEquals(orders.size(),2);
    }


    @Test
    public void updateOrderStatues(){
        Orders order = ordersDao.getOrdersByCustomerId(testOrder.getCustomerId()).get(0);
        ordersDao.updateOrderStatues(order.getId(),"delivered");
        Assert.assertEquals(ordersDao.getOrdersByCustomerId(testOrder.getCustomerId()).get(0).getStatues(),"delivered");
    }
}
