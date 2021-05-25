package com.xingzhi.orders.controller;

import com.xingzhi.orders.model.Orders;
import com.xingzhi.orders.service.CustomerService;
import com.xingzhi.orders.service.OrdersService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/challenge"})
public class CodeChallengeController {
    private Logger logger;
    private CustomerService customerService;
    private OrdersService ordersService;
    @Autowired
    public CodeChallengeController(Logger logger, CustomerService customerService, OrdersService ordersService){
        this.logger = logger;
        this.customerService = customerService;
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/customerOrders/{customerId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Orders> getCustomerOrders(@PathVariable Integer customerId) {
        List<Orders> orders = customerService.getCustomerOrders(customerId);
        return orders;
    }

    @RequestMapping(value = "/productsInDateRange", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Response getProductsSoldInDateRange(@RequestParam("startDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date startDate, @RequestParam("endDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date endDate) {
        Map<Integer, Double> productIdAndQuantity = ordersService.getOrderedProductsInDateRange(startDate,endDate);
        GenericEntity<Map<Integer,Double>> entity = new GenericEntity<Map<Integer,Double>>(productIdAndQuantity){};
        return Response.ok(entity).build();
    }

    @RequestMapping(value = "/createOrder/{customerId}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createOrder(@PathVariable Integer customerId, @RequestParam("productId") List<Integer> productId,@RequestParam("quantity") List<Double> quantity){
        boolean isSuccessful = customerService.createOrders(customerId,productId, quantity);
        if (!isSuccessful) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! The order cannot be created.");
        return ResponseEntity.status(HttpStatus.OK).body(productId);
    }


}
