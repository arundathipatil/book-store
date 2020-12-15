package edu.neu.dao;

import edu.neu.model.Book;
import edu.neu.model.User;

import java.util.List;

public interface BookDao {
    public Book addBook(Book book);
    public Book UpdateBook(Book book);
    public List<Book> listBooks();
    public Book getBook(int id);
    public void deleteBook(int id);
    public Book findById(int id);
    public List<Book> findBooksByemail(User user);
    public Book findBookByEmailAndISBN(User user, String isbn);
    public List<Book> findBooksToBuy(User user);
}
