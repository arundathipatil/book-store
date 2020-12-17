package edu.neu.dao;

import edu.neu.model.Order;
import edu.neu.model.OrderDetail;
import edu.neu.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order placeOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
        return order;
    }

    @Override
    public List<Order> getAllOrdersByUserId(User user) {
        List<Order> orders ;
        Query q = sessionFactory.getCurrentSession().createQuery("FROM Order WHERE user =: user");
        q.setParameter("user", user);
        orders = q.list();
        return orders;
    }

    @Override
    public Order getOrderByOrderId(int id) {
        Order order;
        Query q = sessionFactory.getCurrentSession().createQuery("FROM Order WHERE id =: id");
        q.setParameter("id", id);
        order =  (Order) q.uniqueResult();
        return order;
    }

    @Override
    public List<OrderDetail> getOrderDetail(Order order) {
        List<OrderDetail> orderItems;
        Query q = sessionFactory.getCurrentSession().createQuery("FROM OrderDetail WHERE order =: order");
        q.setParameter("order", order);
        orderItems =  q.list();
        return orderItems;
    }

    @Override
    public OrderDetail addOrderItemToOrderDetails(OrderDetail orderItem) {
        sessionFactory.getCurrentSession().save(orderItem);
        return orderItem;
    }

    @Override
    public List<Order> list() {
        List<Order> orders;
        Query q = sessionFactory.getCurrentSession().createQuery("FROM Order");
        orders = q.list();
        return orders;
    }
}
