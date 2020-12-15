package edu.neu.dao;

import edu.neu.model.Book;
import edu.neu.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
        return book;
    }

    @Override
    public Book UpdateBook(Book book) {
        sessionFactory.getCurrentSession().update(book);
        return book;
    }

    @Override
    public List<Book> listBooks() {
//        Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Book WHERE id = :id");
        return null;
    }


    @Override
    public Book getBook(int id) {
        return null;
    }

    @Override
    public void deleteBook(int id) {
        Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Book WHERE id = :id");
//        q.setInteger("id", id);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public Book findById(int id) {
        Query q =sessionFactory.getCurrentSession().createQuery("FROM Book WHERE id =: id");
//        Query q =sessionFactory.getCurrentSession().createQuery("FROM Book WHERE isbn =:isbn");
        q.setParameter("id", id);;
        Book book=(Book) q.uniqueResult();
        return book;
    }

    @Override
    public List<Book> findBooksByemail(User user) {
        Query q = sessionFactory.getCurrentSession().createQuery("FROM Book WHERE user = :user");
        q.setParameter("user", user);
        List<Book> books = q.list();
        return books;
    }

    @Override
    public Book findBookByEmailAndISBN(User user, String isbn) {
        Query q =sessionFactory.getCurrentSession().createQuery("FROM Book WHERE user = :user and isbn =:isbn");
//        Query q =sessionFactory.getCurrentSession().createQuery("FROM Book WHERE isbn =:isbn");
        q.setParameter("user", user);
        q.setParameter("isbn", isbn);
        Book book=(Book) q.uniqueResult();
        return book;
    }

    @Override
    public List<Book> findBooksToBuy(User user) {
        Query q =sessionFactory.getCurrentSession().createQuery("FROM Book WHERE user != :user");
        q.setParameter("user", user);
        List<Book> books = q.list();
        return  books;
    }
}
