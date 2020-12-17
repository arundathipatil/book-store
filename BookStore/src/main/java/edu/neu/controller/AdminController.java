package edu.neu.controller;

import edu.neu.model.Book;
import edu.neu.model.Order;
import edu.neu.model.User;
import edu.neu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {

    @Autowired
    UserExtracter userExtractor;

    @Autowired
    BookService bookservice;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(@RequestHeader(value="Authorization",required = true) String requestTokenHeader) {
       User user;
       List<User> userList;
       try {
           user = userExtractor.getUserFromtoken(requestTokenHeader);
           if(user.getRole().equals("Admin")) {
                userList = userService.listUsers();
           } else {
               return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
           }
           return ResponseEntity.ok(userList);
       } catch (Exception ex) {
           return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
       }
    }

    @RequestMapping(value= "/updateUserByAdmin", method= RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody User user) {
        User loggedInUser;
        try{
            loggedInUser = userExtractor.getUserFromtoken(requestTokenHeader);
            if(loggedInUser.getRole().equals("Admin")) {
                userService.UpdateUser(user);
                return ResponseEntity.ok(user);
            } else {
                return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
    public ResponseEntity<?> getAllItems(@RequestHeader(value="Authorization",required = true) String requestTokenHeader) {
        User user;
        List<Book> bookList;
        try {
            user = userExtractor.getUserFromtoken(requestTokenHeader);
            if(user.getRole().equals("Admin")) {
                bookList = bookservice.listBooks();
            } else {
                return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok(bookList);
        } catch (Exception ex) {
            return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value= "/updateBookByAdmin", method= RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody Book book) {
        User user;
        List<Book> bookList;
        try {
            user = userExtractor.getUserFromtoken(requestTokenHeader);
            if(user.getRole().equals("Admin")) {
                bookservice.UpdateBook(book);
                return ResponseEntity.ok(book);
            } else {
                return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/getAllOrders" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllOrders(@RequestHeader(value="Authorization",required = true) String requestTokenHeader) {
        User user;
        List<Order> orderList;
        try {
            user = userExtractor.getUserFromtoken(requestTokenHeader);
            if(user.getRole().equals("Admin")) {
                orderList = orderService.list();
            } else {
                return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok(orderList);
        } catch (Exception ex) {
            return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
        }
    }


}
