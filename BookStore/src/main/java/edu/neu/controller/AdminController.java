package edu.neu.controller;

import edu.neu.model.Book;
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


}
