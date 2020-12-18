package edu.neu.service;

import java.util.List;

import edu.neu.model.User;
import edu.neu.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userdao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User addUser(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userdao.addUser(user);
    }

    @Override
    public List<User> listUsers() {
        return  userdao.listUsers();
    }

    @Override
    public User getUser(String uname) {
        return null;
    }

    @Override
    public User getUserfromemail(String email) {
        return userdao.findByemail(email);
    }

    @Override
    public void deleteUser(String name) {

    }

    @Override
    public void UpdateUser(User user) {
        User newUserObj=new User();
        newUserObj.setEmail(user.getEmail());
        newUserObj.setFirstName(user.getFirstName());
        newUserObj.setLastName(user.getLastName());
        newUserObj.setPassword(user.getPassword());
        newUserObj.setRole(user.getRole());
        newUserObj.setAddress(user.getAddress());
        userdao.UpdateUser(newUserObj);
    }
}
