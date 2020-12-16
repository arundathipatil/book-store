package edu.neu.dao;

import edu.neu.model.Book;
import edu.neu.model.Cart;
import edu.neu.model.CartItem;
import edu.neu.model.User;

public interface CartDao {
    public Cart createCart(Cart cart);
    public Cart getCartByUser(User user);
    public CartItem addBookToCart(CartItem cartItem);
    public void deleteBookFromCart(int id);
    public Cart updateCart(Cart cart);
    public void deleteAllItemsFromCart(Cart cart);
}
