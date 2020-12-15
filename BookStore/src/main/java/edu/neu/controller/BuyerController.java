package edu.neu.controller;

import edu.neu.model.Book;
import edu.neu.model.CartItem;
import edu.neu.model.Order;
import edu.neu.model.User;
import edu.neu.service.BookService;
import edu.neu.service.CartService;
import edu.neu.service.OrderService;
import edu.neu.service.UserExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BuyerController {

    @Autowired
    UserExtracter userExtractor;

    @Autowired
    BookService bookservice;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value= "/booksToBuy", method= RequestMethod.GET)
    public ResponseEntity<?> getBooksToBuy(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestParam String email) {
        User user;
        List<Book> books = new ArrayList<>();
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            Comparator<Book> compareByNameAndPrice = Comparator.comparing(Book::getTitle).thenComparing(Book::getPrice);
            books = bookservice.findBooksToBuy(user)
                    .stream()
                    .filter(a -> a.getQuantity() !=0)
                    .sorted(compareByNameAndPrice)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(books);
    }

    @RequestMapping(value= "/addBookToCart", method= RequestMethod.POST)
    public ResponseEntity<?> addBookToCart(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody CartItem cartItem) {
        User user;
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            cartService.addBookToCart(user, cartItem);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cartItem);
    }

    @RequestMapping(value = "/getCartItems", method = RequestMethod.GET)
    public ResponseEntity<?> getCartItems(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestParam String email) {
        User user;
        List<CartItem> cartItems = new ArrayList<>();
        try{
            user=userExtractor.getUserFromtoken(requestTokenHeader);
           cartItems = cartService.getCartItems(user);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cartItems);
    }

    @RequestMapping(value = "/deleteCartItem", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemFromCart(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestParam int id) {
        User user;
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            cartService.deleteBookFromCart(id);
            return ResponseEntity.ok(id);

        }catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
    public ResponseEntity<?> confirmOrder(@RequestHeader(value="Authorization",required = true) String requestTokenHeader) {
        User user;
        Order order;
        try{
            user = userExtractor.getUserFromtoken(requestTokenHeader);
            order = orderService.confirmOrder(user);
            return ResponseEntity.ok(order);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseEntity<?> placeOrder(@RequestHeader(value="Authorization",required = true) String requestTokenHeader) {
        User user;
        Order order;
        try {
            user = userExtractor.getUserFromtoken(requestTokenHeader);
            order = orderService.placeOrder(user);
            return ResponseEntity.ok(order);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
