package com.hotwax.task.service;

import com.hotwax.task.entity.OrderHeader;
import com.hotwax.task.entity.OrderItem;
import com.hotwax.task.exception.ResourceNotFoundException;
import com.hotwax.task.repository.OrderHeaderRepository;
import com.hotwax.task.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderHeader createOrder(OrderHeader order) {
        // Set the order reference in items to ensure bidirectional link is established
        // for persistence

        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
            }
        }
        return orderHeaderRepository.save(order);
    }

    @Override
    public OrderHeader getOrder(Integer orderId) {
        return orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public OrderHeader updateOrder(Integer orderId, OrderHeader orderDetails) {
        OrderHeader order = getOrder(orderId);

        // Update fields allowed to be updated. Assuming customer and date might not
        // change often,
        // but shipping/billing details do.
        order.setShippingContactMechId(orderDetails.getShippingContactMechId());
        order.setBillingContactMechId(orderDetails.getBillingContactMechId());

        // Optionally update other fields if needed, e.g. status if it existed on header

        return orderHeaderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        OrderHeader order = getOrder(orderId);
        orderHeaderRepository.delete(order);
    }

    @Override
    public OrderItem addOrderItem(Integer orderId, OrderItem orderItem) {
        OrderHeader order = getOrder(orderId);
        orderItem.setOrder(order);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Integer orderId, Integer orderItemSeqId, OrderItem itemDetails) {
        // Verify order exists
        getOrder(orderId);

        OrderItem item = orderItemRepository.findById(orderItemSeqId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with id: " + orderItemSeqId));

        // Ensure item belongs to the order
        if (!item.getOrder().getOrderId().equals(orderId)) {
            throw new IllegalArgumentException("Order Item does not belong to the specified Order");
        }

        item.setQuantity(itemDetails.getQuantity());
        item.setStatus(itemDetails.getStatus());

        return orderItemRepository.save(item);
    }

    @Override
    public void deleteOrderItem(Integer orderId, Integer orderItemSeqId) {
        // Verify order exists
        getOrder(orderId);

        OrderItem item = orderItemRepository.findById(orderItemSeqId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with id: " + orderItemSeqId));

        if (!item.getOrder().getOrderId().equals(orderId)) {
            throw new IllegalArgumentException("Order Item does not belong to the specified Order");
        }

        orderItemRepository.delete(item);
    }
}
