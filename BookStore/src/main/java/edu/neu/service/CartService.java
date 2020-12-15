package edu.neu.service;

import edu.neu.model.Cart;
import edu.neu.model.CartItem;
import edu.neu.model.User;

import java.util.List;

public interface CartService {
    public Cart createCart(Cart cart);
    public CartItem addBookToCart(User user, CartItem cartItem);
    public List<CartItem> getCartItems(User u);
    public void deleteBookFromCart(int id);
}
