package com.softeng2.PantherCafe.Order;

import com.softeng2.PantherCafe.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "api/order")
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService, EmployeeService employeeService) {
        this.orderService = orderService;

    }

    @GetMapping()
    public List<Orders> getAllOrders() {
        return orderService.getOrders();
    }

    @PostMapping()
    public void addOrder(@RequestBody Orders order) {
        orderService.addNewOrder(order);
    }

    @DeleteMapping(path = "{orderID}")
    public void deleteOrder(@PathVariable("orderID") Long orderID) {
        orderService.deleteOrder(orderID);
    }

}
