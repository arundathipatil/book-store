package edu.neu.controller;

import edu.neu.model.Cart;
import edu.neu.model.Changepwd;
import edu.neu.model.User;
import edu.neu.service.CartService;
import edu.neu.service.UserExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.neu.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserExtracter userExtractor;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    CartService cartService;

    @RequestMapping(value= "/user", method= RequestMethod.GET)
    public ResponseEntity<?> get(@RequestHeader(value="Authorization",required = true) String requestTokenHeader) {
        User user = new User();
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
        } catch (Exception ex) {

            return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value= "/user", method= RequestMethod.POST)
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        User u;
       try {
           if(!validateUserInput(user)) {
               return new ResponseEntity<>("Invalid User Input!Please Correct Input and retry" , HttpStatus.BAD_REQUEST);
           }
           u = userService.addUser(user);
       } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage() , HttpStatus.BAD_REQUEST);
       }
        if(user.getRole().equals("SB")) {
            createCart(user);
        }

       return ResponseEntity.ok(u);
    }

    @RequestMapping(value= "/user", method= RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody User u) {
        User user = new User();
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            if(user.getEmail().equals(u.getEmail())) {
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
                if(!validateUserInput(user)) {
                    return new ResponseEntity<>("Invalid User Input!Please Correct Input and retry" , HttpStatus.BAD_REQUEST);
                }
                userService.UpdateUser(u);
            } else {
                return new ResponseEntity<String>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value= "/changePwd", method= RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody Changepwd pwd) {
        User user = new User();
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            if(user.getEmail().equals(pwd.getEmail())) {
                user.setPassword(bcryptEncoder.encode(pwd.getNewPassword()));
                String regex1 = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,32}$";
                if(!isValidPassword(pwd.getNewPassword(), regex1)) {
                    return new ResponseEntity<>("Invalid User Input!Please Correct Input and retry" , HttpStatus.BAD_REQUEST);
                }
                userService.UpdateUser(user);
            } else {
                return new ResponseEntity<String>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    private void createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cartService.createCart(cart);
    }

    private boolean validateUserInput(User u) {
        if(u == null){
            return false;
        }
        if((u.getFirstName().equals(null) || u.getFirstName().equals(""))) {
            return false;
        }
        if(!u.getFirstName().matches( "[A-Z][a-z]*" ) || u.getFirstName().matches("<script>(.*?)</script>") || u.getFirstName().matches("\"<script(.*?)>\"")) {
            return false;
        }

        if(u != null && (u.getLastName().equals(null) || u.getLastName().equals(""))) {
            return false;
        }

        if(!u.getLastName().matches( "[A-Z][a-z]*" ) || u.getLastName().matches("<script>(.*?)</script>") || u.getLastName().matches("\"<script(.*?)>\"")) {
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

        String regex1 = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,32}$";

        if(!isValidPassword(u.getPassword(), regex1)) {
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

    public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
