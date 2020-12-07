package edu.neu.controller;

import edu.neu.JwtTokenUtil;
import edu.neu.model.JwtRequest;
import edu.neu.model.JwtResponse;
import edu.neu.model.User;
import edu.neu.service.UserDetailService;
import edu.neu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @RequestMapping(value= "/authenticate", method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        User user = userService.getUserfromemail(authenticationRequest.getEmail());
        if(user==null) {
                return new ResponseEntity<>("UNAUTHORIZED" , HttpStatus.UNAUTHORIZED);
        }
        try {
            authenticate(user.getEmail(), authenticationRequest.getPassword());
        } catch (DisabledException ex) {
            return new ResponseEntity<>("DisabledException" , HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("BadCredentialsException" , HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<JwtResponse>(new JwtResponse(token,user),HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

    }

//    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE,value = "/authenticate", method = RequestMethod.POST)
}
