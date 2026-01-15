package com.hotwax.task.service;

import com.hotwax.task.entity.OrderHeader;
import com.hotwax.task.entity.OrderItem;

public interface OrderService {
    OrderHeader createOrder(OrderHeader order);

    OrderHeader getOrder(Integer orderId);

    OrderHeader updateOrder(Integer orderId, OrderHeader orderDetails);

    void deleteOrder(Integer orderId);

    OrderItem addOrderItem(Integer orderId, OrderItem orderItem);

    OrderItem updateOrderItem(Integer orderId, Integer orderItemSeqId, OrderItem itemDetails);

    void deleteOrderItem(Integer orderId, Integer orderItemSeqId);
}
