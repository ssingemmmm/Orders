package com.xingzhi.orders.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.xingzhi.orders"})
public class AppInitializer extends SpringBootServletInitializer {
    public static void main(String[] args) throws NullPointerException{
//        if (HibernateUtil.getSessionFactory() == null) {
//            throw new NullPointerException("The Hibernate session factory is NULL!");
//        }
        SpringApplication.run(AppInitializer.class, args);
    }
}
