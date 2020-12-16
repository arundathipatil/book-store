package edu.neu.service;

import edu.neu.model.Order;
import edu.neu.model.OrderDTO;
import edu.neu.model.OrderDetail;
import edu.neu.model.User;

import java.util.List;

public interface OrderService {
    public Order confirmOrder(User user) throws Exception;
    public Order placeOrder(User user) throws Exception;
    public List<Order> getAllOrdersByUserId(User user);
    public List<OrderDetail> getOrderDetails(int orderId);
    public OrderDTO confirmOrderDTO(User user) throws Exception;
}
