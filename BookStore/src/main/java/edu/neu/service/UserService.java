package edu.neu.service;

import edu.neu.model.User;

import java.util.List;

public interface UserService {

    public User addUser(User user);

    public List<User> listUsers();

    public User getUser(String uname);

    public User getUserfromemail(String email);

    public void deleteUser(String name);
    public void UpdateUser(User user);
}
