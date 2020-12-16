package edu.neu.dao;

import edu.neu.model.User;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public User UpdateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }

    @Override
    public List<User> listUsers() {
        Query q = sessionFactory.getCurrentSession().createQuery("From User");
        List<User> users = q.list();
        return users;
    }

    @Override
    public User getUser(String username) {

        sessionFactory.getCurrentSession().get(User.class, username);
        return null;
    }

    @Override
    public void deleteUser(String name) {

    }

    @Override
    public User findByuname(String uname) {
        return null;
    }

    @Override
    public User findByemail(String email) {
        Query q =sessionFactory.getCurrentSession().createQuery("FROM User WHERE email = :email");
        q.setParameter("email", email);
        User user=(User) q.uniqueResult();
        return user;
    }
}
