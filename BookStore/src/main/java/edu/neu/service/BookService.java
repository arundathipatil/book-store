package edu.neu.service;

import edu.neu.model.Book;
import edu.neu.model.User;

import java.util.List;

public interface BookService {
    public Book addBook(Book book);
    public List<Book> listBooks();
    public Book getBook(int id);
    public List<Book> getBookfromemail(User user);
    public void deleteBook(int id);
    public void UpdateBook(Book book);
    public Book findBookByEmailAndISBN(User user, String isbn);
    public List<Book> findBooksToBuy(User user);
}
