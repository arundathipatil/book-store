package edu.neu.service;

import edu.neu.model.Order;
import edu.neu.model.User;

public interface OrderService {
    public Order confirmOrder(User user) throws Exception;
    public Order placeOrder(User user) throws Exception;
}
