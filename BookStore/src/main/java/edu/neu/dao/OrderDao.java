package edu.neu.dao;

import edu.neu.model.Order;
import edu.neu.model.OrderDetail;
import edu.neu.model.User;

import java.util.List;

public interface OrderDao {
    public Order placeOrder(Order order);
    public List<Order> getAllOrdersByUserId(User user);
    public Order getOrderByOrderId(int id);
    public List<OrderDetail> getOrderDetail(Order order);
    public OrderDetail addOrderItemToOrderDetails(OrderDetail orderItem);
    public List<Order> list();
}
