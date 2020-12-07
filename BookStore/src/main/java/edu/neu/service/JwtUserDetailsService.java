package edu.neu.service;

import edu.neu.model.UserDTO;
import edu.neu.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailService {
    @Autowired
    private UserDao UserDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        edu.neu.model.User user = UserDao.findByemail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public edu.neu.model.User save(UserDTO user) {
        return null;
    }
}
