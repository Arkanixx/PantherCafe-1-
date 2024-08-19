package com.softeng2.PantherCafe.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }

    public void addNewOrder(Orders order) {
        Optional<Orders> orderByID = orderRepository.findByOrderID(order.getOrderId());

        if (orderByID.isPresent()) {
            throw new RuntimeException("Order already exists");
        }

        orderRepository.save(order);
    }


    public void deleteOrder(Long orderID) {
        boolean exist = orderRepository.existsById(orderID);
        if (!exist) {
            throw new RuntimeException("Order does not exist");
        }else {
            orderRepository.deleteById(orderID);
        }
    }
}
