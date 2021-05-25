package com.xingzhi.orders.service;

import com.xingzhi.orders.model.Customer;
import com.xingzhi.orders.model.Orders;
import com.xingzhi.orders.model.Product;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    boolean updateCustomerName(Integer id, String name);
    List<Customer> getCustomers();
    Customer getCustomerByName(String name);
    Customer getCustomerById(Integer id);
    boolean deleteCustomerByName(String name);
    boolean deleteCustomerById(Integer id);
    boolean createOrders(Integer customerId,List<Integer> products, List<Double> quantity);
    List<Orders> getCustomerOrders(Integer customerId);
}
