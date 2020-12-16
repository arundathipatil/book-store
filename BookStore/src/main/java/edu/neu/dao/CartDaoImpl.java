package edu.neu.dao;

import edu.neu.model.Cart;
import edu.neu.model.CartItem;
import edu.neu.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("cartDao")
public class CartDaoImpl implements CartDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Cart createCart(Cart cart) {
        sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    @Override
    public Cart getCartByUser(User user) {
        org.hibernate.query.Query q = sessionFactory.getCurrentSession().createQuery("FROM Cart WHERE user =:user");
        q.setParameter("user", user);
        Cart cart = (Cart) q.uniqueResult();
        return cart;
    }

    @Override
    public CartItem addBookToCart(CartItem cartItem) {
        sessionFactory.getCurrentSession().save(cartItem);
        return cartItem;
    }

    @Override
    public void deleteBookFromCart(int id) {
        Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM CartItem WHERE id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public Cart updateCart(Cart cart) {
        sessionFactory.getCurrentSession().update(cart);
        return cart;
    }

    @Override
    public void deleteAllItemsFromCart(Cart cart) {
        Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM CartItem WHERE shoppingCart =: cart");
        q.setParameter("cart", cart);
        q.executeUpdate();
    }
}
