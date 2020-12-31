package edu.neu.controller;

import edu.neu.model.Book;
import edu.neu.model.Order;
import edu.neu.model.User;
import edu.neu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                if(!validateUpdateUserInput(user)) {
                    return new ResponseEntity<>("Invalid User Input!Please Correct Input and retry" , HttpStatus.BAD_REQUEST);
                }
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
                boolean t = checkIfValidBookInput(book);
                if(!t) {
                    return new ResponseEntity<>("Invalid Input! Please correct your inputs and re-submit", HttpStatus.BAD_REQUEST);
                }
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

    @RequestMapping(value = "/cancelOrderByAdmin" , method = RequestMethod.POST)
    public ResponseEntity<?> cancelOrder(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody Order order) {
        User user;
        try {
            user = userExtractor.getUserFromtoken(requestTokenHeader);
            if(user.getRole().equals("Admin")) {
                order = orderService.cancelOrder(order);
            } else {
                return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok(order);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage() +" :UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean validateUpdateUserInput(User u) {
        if(u == null){
            return false;
        }
        if((u.getFirstName().equals(null) || u.getFirstName().equals(""))) {
            return false;
        }
        if(!u.getFirstName().matches( "[A-Za-z ]*" ) || u.getFirstName().matches("<script>(.*?)</script>") || u.getFirstName().matches("\"<script(.*?)>\"")) {
            return false;
        }

        if(u != null && (u.getLastName().equals(null) || u.getLastName().equals(""))) {
            return false;
        }

        if(!u.getLastName().matches( "[A-Za-z ]*" ) || u.getLastName().matches("<script>(.*?)</script>") || u.getLastName().matches("\"<script(.*?)>\"")) {
            return false;
        }

        if(u.getEmail().equals(null) || u.getEmail().equals("")) {
            return false;
        }

        if(!u.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return false;
        }

        if(u.getPassword().equals(null) || u.getPassword().equals("")) {
            return false;
        }

        if(u.getPassword().matches("<script>(.*?)</script>") || u.getPassword().matches("\"<script(.*?)>\"")) {
            return false;
        }
        boolean isValidRole = false;
        if(u.getRole().equals("Admin") || u.getRole().equals("SB")) {
            isValidRole = true;
        } else {
            return false;
        }

        return true;
    }

    private boolean checkIfValidBookInput(Book book) {
        if(book == null) {
            return false;
        }

        if(book.getIsbn().equals(null) || book.getIsbn().equals("")) {
            return false;
        }

        if(!book.getIsbn().matches("^[0-9]*$") || book.getIsbn().matches("<script>(.*?)</script>") || book.getIsbn().matches("\"<script(.*?)>\"")) {
            return false;
        }

        if(book.getTitle().equals(null) || book.getTitle().equals("")) {
            return false;
        }

        if(!book.getTitle().matches("[A-Za-z ]*") || book.getTitle().matches("<script>(.*?)</script>") || book.getTitle().matches("\"<script(.*?)>\"")) {
            return false;
        }

        if(book.getAuthors().equals(null) || book.getAuthors().equals("")) {
            return false;
        }

        if(!book.getAuthors().matches("[A-Za-z ]*") || book.getAuthors().matches("<script>(.*?)</script>") || book.getAuthors().matches("\"<script(.*?)>\"")) {
            return false;
        }
        if(book.getPublicationDate().equals(null) || book.getPublicationDate().equals("")) {
            return false;
        }
        return true;
    }

    private boolean isValidDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date.toString());
            return true;
        }
        catch(ParseException e){
            return false;
        }

    }

}
