package com.xingzhi.orders.JDBC;

import com.xingzhi.orders.model.Category;
import com.xingzhi.orders.model.Customer;

import java.util.List;

public interface CustomerDao {
    boolean addCustomer(Customer customer);
    boolean updateCustomerName(Integer id, String name);
    List<Customer> getCustomers();
    Customer getCustomerByName(String name);
    Customer getCustomerById(Integer id);
    boolean deleteCustomerByName(String name);
    boolean deleteCustomerById(Integer id);
}
