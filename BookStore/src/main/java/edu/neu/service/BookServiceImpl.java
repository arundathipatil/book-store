package edu.neu.service;

import edu.neu.dao.BookDao;
import edu.neu.dao.UserDao;
import edu.neu.model.Book;
import edu.neu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookdao;

    @Override
    public Book addBook(Book book) {
        return bookdao.addBook(book);
    }

    @Override
    public List<Book> listBooks() {
        return null;
    }

    @Override
    public Book getBook(int id) {
        return null;
    }

    @Override
    public List<Book> getBookfromemail(User user) {

        return bookdao.findBooksByemail(user);
    }

    @Override
    public void deleteBook(int id) {
        bookdao.deleteBook(id);
    }

    @Override
    public void UpdateBook(Book book) {
        bookdao.UpdateBook(book);
    }

    @Override
    public Book findBookByEmailAndISBN(User user, String isbn) {
        return bookdao.findBookByEmailAndISBN(user, isbn);
    }

    @Override
    public List<Book> findBooksToBuy(User user) {
        return bookdao.findBooksToBuy(user);
    }
}
