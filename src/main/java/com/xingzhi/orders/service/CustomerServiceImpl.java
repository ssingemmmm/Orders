package com.xingzhi.orders.service;

import com.xingzhi.orders.JDBC.CustomerDao;
import com.xingzhi.orders.JDBC.OrderedProductDao;
import com.xingzhi.orders.JDBC.OrdersDao;
import com.xingzhi.orders.model.Customer;
import com.xingzhi.orders.model.OrderedProduct;
import com.xingzhi.orders.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerServiceImpl implements CustomerService{
    private CustomerDao customerDao;
    private OrdersDao ordersDao;
    private OrderedProductDao orderedProductDao;
    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, OrdersDao ordersDao, OrderedProductDao orderedProductDao){
        this.customerDao = customerDao;
        this.ordersDao = ordersDao;
        this.orderedProductDao = orderedProductDao;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public boolean updateCustomerName(Integer id, String name) {
        return customerDao.updateCustomerName(id,name);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerDao.getCustomerByName(name);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public boolean deleteCustomerByName(String name) {
        return customerDao.deleteCustomerByName(name);
    }

    @Override
    public boolean deleteCustomerById(Integer id) {
        return deleteCustomerById(id);
    }

    @Override
    public boolean createOrders(Integer customerId, List<Integer> products, List<Double> quantity) {
        boolean isSuccessful = false;
        try {
            Orders newOrder = new Orders();
            newOrder.setCustomerId(customerId);
            newOrder.setDate(Date.valueOf(LocalDate.now()));
            ordersDao.addOrder(newOrder);
            int newOrderId = 0;
            List<Orders> customerOrders = getCustomerOrders(customerId);
            for (Orders o : customerOrders) {
                if (o.getId() > newOrderId) {
                    newOrderId = o.getId();
                }
            }
            for (int i = 0; i < products.size(); i++) {
                OrderedProduct orderedProduct = new OrderedProduct();
                orderedProduct.setProductId(products.get(i));
                orderedProduct.setOrderId(newOrderId);
                orderedProduct.setQuantity(quantity.get(i));
                orderedProductDao.addOrderedProduct(orderedProduct);
            }
            isSuccessful = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public List<Orders> getCustomerOrders(Integer customerId) {
        return ordersDao.getOrdersByCustomerId(customerId);
    }
}
