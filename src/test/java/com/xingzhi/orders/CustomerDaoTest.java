package com.xingzhi.orders;

import com.xingzhi.orders.JDBC.CustomerDao;
import com.xingzhi.orders.init.AppInitializer;
import com.xingzhi.orders.model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;
    private Customer testCustomer;
    private String testCustomerName;
    @Before
    public void init() {
        testCustomer = new Customer();
        testCustomerName = "test";
        testCustomer.setName(testCustomerName);
        customerDao.addCustomer(testCustomer);
    }

    @After
    public void tearDown(){
        customerDao.deleteCustomerByName(testCustomerName);
    }

    @Test
    public void getCustomers(){
        List<Customer> customers = customerDao.getCustomers();
        Assert.assertEquals(customers.size(),5);
    }

    @Test
    public void getCustomerByName(){
        Customer customer = customerDao.getCustomerByName(testCustomer.getName());
        Assert.assertNotNull(customer);
    }

    @Test
    public void getCustomerById(){
        Customer customer = customerDao.getCustomerById(0);
        Assert.assertEquals(customer.getName(),testCustomer.getName());
    }

    @Test
    public void updateCustomerName(){
        Customer customer = customerDao.getCustomerByName(testCustomer.getName());
        customerDao.updateCustomerName(customer.getId(),"test2");
        testCustomerName = "test2";
        Assert.assertEquals(customerDao.getCustomerById(customer.getId()).getName(),"test2");

    }
}

