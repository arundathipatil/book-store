package edu.neu.model;

import javax.persistence.*;

@Entity
@Table(name="cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Book book;

    private int quantity;

    @ManyToOne
    private Cart shoppingCart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
