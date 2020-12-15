package edu.neu.dao;

import edu.neu.model.Order;

public interface OrderDao {
    public Order placeOrder(Order order);
}
