package com.hotwax.task.controller;

import com.hotwax.task.entity.OrderHeader;
import com.hotwax.task.entity.OrderItem;
import com.hotwax.task.service.OrderService;
import com.hotwax.task.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    // Create an Order
    @PostMapping
    public ResponseEntity<OrderHeader> createOrder(@RequestBody OrderHeader order) {
        OrderHeader createdOrder = orderServiceImpl.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // Retrieve Order Details
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderHeader> getOrder(@PathVariable Integer orderId) {
        OrderHeader order = orderServiceImpl.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Update an Order
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderHeader> updateOrder(@PathVariable Integer orderId,
            @RequestBody OrderHeader orderDetails) {
        OrderHeader updatedOrder = orderServiceImpl.updateOrder(orderId, orderDetails);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // Delete an Order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderServiceImpl.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add an Order Item
    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItem> addOrderItem(@PathVariable Integer orderId, @RequestBody OrderItem orderItem) {
        OrderItem createdItem = orderServiceImpl.addOrderItem(orderId, orderItem);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    // Update an Order Item
    @PutMapping("/{orderId}/items/{orderItemSeqId}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable Integer orderId,
            @PathVariable Integer orderItemSeqId,
            @RequestBody OrderItem itemDetails) {
        OrderItem updatedItem = orderServiceImpl.updateOrderItem(orderId, orderItemSeqId, itemDetails);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // Delete an Order Item
    @DeleteMapping("/{orderId}/items/{orderItemSeqId}")
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable Integer orderId,
            @PathVariable Integer orderItemSeqId) {
        orderServiceImpl.deleteOrderItem(orderId, orderItemSeqId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
