package edu.neu.dao;

import edu.neu.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order placeOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
        return order;
    }
}
