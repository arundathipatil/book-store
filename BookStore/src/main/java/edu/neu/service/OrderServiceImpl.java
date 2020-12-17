package edu.neu.service;

import edu.neu.dao.BookDao;
import edu.neu.dao.CartDao;
import edu.neu.dao.OrderDao;
import edu.neu.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    CartDao cartdao;

    @Autowired
    BookDao bookdao;

    @Autowired
    OrderDao orderDao;

    @Override
    public Order confirmOrder(User user) throws Exception {

        Cart cart = cartdao.getCartByUser(user);

        List<CartItem> cartItems = cart.getCartItems();

        if(cartItems != null) {
            for(int i=0; i< cartItems.size(); i++) {
                CartItem item = cartItems.get(i);
                Book book = bookdao.findById(item.getBook().getId());
                if(book !=null) {
                    if(book.getQuantity() < item.getQuantity()) {
                        throw new Exception("Book with isbn " + book.getIsbn() +": quantity selected  not available for sale");
                    }
                } else {
                    throw new Exception("Book with isbn " + book.getIsbn() +" Not available for sale");
                }
            }
        } else {
            throw new Exception("Add Items to cart to place the order");
        }
        Order o = new Order();
        Double orderTotalPrice =0.0;
        List<OrderDetail> orderItems = new ArrayList<>();
        o.setTotalPrice(2);
        o.setUser(user);
        for(int i=0; i< cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            OrderDetail orderItem = new OrderDetail();
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            orderTotalPrice = orderTotalPrice + (item.getBook().getPrice() * item.getQuantity());
            orderItems.add(orderItem);
        }
        o.setOrderItems(orderItems);
        o.setTotalPrice(orderTotalPrice);
        return  o;
    }

    @Override
    public Order placeOrder(User user) throws Exception {
        Order order = confirmOrder(user);
        order = orderDao.placeOrder(order);
        for(int i=0; i< order.getOrderItems().size(); i++) {
            order.getOrderItems().get(i).setOrder(order);
            orderDao.addOrderItemToOrderDetails(order.getOrderItems().get(i));
        }
        for(int i=0; i< order.getOrderItems().size(); i++) {
            Book b = order.getOrderItems().get(i).getBook();
            b.setQuantity(b.getQuantity() - order.getOrderItems().get(i).getQuantity());
            bookdao.UpdateBook(b);
        }
        Cart cart = cartdao.getCartByUser(user);
        cartdao.deleteAllItemsFromCart(cart);
//        cart.setCartItems(new ArrayList<>());
//        cartdao.updateCart(cart);
        return order;
    }

    @Override
    public List<Order> getAllOrdersByUserId(User user) {
        return orderDao.getAllOrdersByUserId(user);
    }

    @Override
    public List<OrderDetail> getOrderDetails(int orderId) {
        Order order = orderDao.getOrderByOrderId(orderId);
        List<OrderDetail> orderItems = orderDao.getOrderDetail(order);
        return orderItems;
    }

    @Override
    public OrderDTO confirmOrderDTO(User user) throws Exception {

        Cart cart = cartdao.getCartByUser(user);

        List<CartItem> cartItems = cart.getCartItems();

        if(cartItems != null) {
            for(int i=0; i< cartItems.size(); i++) {
                CartItem item = cartItems.get(i);
                Book book = bookdao.findById(item.getBook().getId());
                if(book !=null) {
                    if(book.getQuantity() < item.getQuantity()) {
                        throw new Exception("Book with isbn " + book.getIsbn() +": quantity selected  not available for sale");
                    }
                } else {
                    throw new Exception("Book with isbn " + book.getIsbn() +" Not available for sale");
                }
            }
        } else {
            throw new Exception("Add Items to cart to place the order");
        }
        OrderDTO o = new OrderDTO();
        Double orderTotalPrice =0.0;
        List<OrderDetail> orderItems = new ArrayList<>();
        o.setTotalPrice(2);
        o.setUser(user);
        for(int i=0; i< cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            OrderDetail orderItem = new OrderDetail();
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            orderTotalPrice = orderTotalPrice + (item.getBook().getPrice() * item.getQuantity());
            orderItems.add(orderItem);
        }
        o.setOrderItems(orderItems);
        o.setTotalPrice(orderTotalPrice);
        return  o;
    }

    @Override
    public List<Order> list() {
        return orderDao.list();
    }


}
