package edu.neu.service;

import edu.neu.dao.CartDao;
import edu.neu.dao.UserDao;
import edu.neu.model.Cart;
import edu.neu.model.CartItem;
import edu.neu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartdao;

    @Override
    public Cart createCart(Cart cart) {
        return cartdao.createCart(cart);
    }

    @Override
    public CartItem addBookToCart(User user, CartItem cartItem) {
        Cart cart = cartdao.getCartByUser(user);
        cartItem.setShoppingCart(cart);
        return cartdao.addBookToCart(cartItem);
    }

    @Override
    public List<CartItem> getCartItems(User user) {
        Cart cart = cartdao.getCartByUser(user);
        return cart.getCartItems();
    }

    @Override
    public void deleteBookFromCart(int id) {
        cartdao.deleteBookFromCart(id);
    }


}
