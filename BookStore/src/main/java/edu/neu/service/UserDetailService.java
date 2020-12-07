package edu.neu.service;

import edu.neu.model.User;
import edu.neu.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserDetailService extends UserDetailsService{
	public User save(UserDTO user);
}

