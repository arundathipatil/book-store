package edu.neu.controller;

import edu.neu.model.Changepwd;
import edu.neu.model.User;
import edu.neu.service.UserExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
           u = userService.addUser(user);
       } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage() , HttpStatus.BAD_REQUEST);
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
                userService.UpdateUser(user);
            } else {
                return new ResponseEntity<String>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

}
