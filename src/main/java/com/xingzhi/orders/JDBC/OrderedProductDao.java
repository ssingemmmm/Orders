package com.xingzhi.orders.JDBC;
import com.xingzhi.orders.model.OrderedProduct;

import java.util.List;

public interface OrderedProductDao {
    boolean addOrderedProduct(OrderedProduct orderedProduct);
    boolean updateOrderedProductId(Integer id, Integer productId);
    boolean updateOrderedProductQuantity(Integer id, Double quantity);
    List<OrderedProduct> getOrderedProductsByOrderId(Integer orderId);
    List<OrderedProduct> getOrderedProduct();
    boolean deleteOrderedProductByOrderId(Integer orderId);
    boolean deleteOrderedProductById(Integer id);
}
