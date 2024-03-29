package edu.neu.controller;

import edu.neu.model.Book;
import edu.neu.model.User;
import edu.neu.service.BookService;
import edu.neu.service.UserExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class SellerController {

    @Autowired
    UserExtracter userExtractor;

    @Autowired
    BookService bookservice;

    @RequestMapping(value= "/book", method= RequestMethod.POST)
    public ResponseEntity<?> saveBook(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody Book book) {

        User user = new User();
        boolean t = checkIfValidBookInput(book);
        if(!t) {
            return new ResponseEntity<>("Invalid Input! Please correct your inputs and re-submit", HttpStatus.BAD_REQUEST);
        }
        Book bookadded;
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            String isbn = book.getIsbn();
            Book b = bookservice.findBookByEmailAndISBN(user, isbn);
            if(b != null) {
                return new ResponseEntity<>("Book With ISBN "+ book.getIsbn()+" already exsists. Try Editing the existing Book", HttpStatus.BAD_REQUEST);
            } else {
                book.setUser(user);
                bookadded= bookservice.addBook(book);
            }
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(bookadded);
    }

    @RequestMapping(value= "/book", method= RequestMethod.GET)
    public ResponseEntity<?> getAllBooksByEmail(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestParam String email) throws Exception {
        User user = new User();
        List<Book> books;
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            books = bookservice.getBookfromemail(user);
            return ResponseEntity.ok(books);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value= "/book", method= RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestBody Book book) {
        User user;
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            boolean t = checkIfValidBookInput(book);
            if(!t) {
                return new ResponseEntity<>("Invalid Input! Please correct your inputs and re-submit", HttpStatus.BAD_REQUEST);
            }
            bookservice.UpdateBook(book);
            return ResponseEntity.ok(book);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value= "/book", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@RequestHeader(value="Authorization",required = true) String requestTokenHeader, @RequestParam int id) {
        User user;
        try {
            user=userExtractor.getUserFromtoken(requestTokenHeader);
            bookservice.deleteBook(id);
            return ResponseEntity.ok(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean checkIfValidBookInput(Book book) {
        if(book == null) {
            return false;
        }

        if(book.getIsbn().equals(null) || book.getIsbn().equals("")) {
            return false;
        }

        if(!book.getIsbn().matches("^[0-9]*$") || book.getIsbn().matches("<script>(.*?)</script>") || book.getIsbn().matches("\"<script(.*?)>\"")) {
            return false;
        }

        if(book.getTitle().equals(null) || book.getTitle().equals("")) {
            return false;
        }

        if(!book.getTitle().matches("[A-Za-z ]*") || book.getTitle().matches("<script>(.*?)</script>") || book.getTitle().matches("\"<script(.*?)>\"")) {
            return false;
        }

        if(book.getAuthors().equals(null) || book.getAuthors().equals("")) {
            return false;
        }

        if(!book.getAuthors().matches("[A-Za-z ]*") || book.getAuthors().matches("<script>(.*?)</script>") || book.getAuthors().matches("\"<script(.*?)>\"")) {
            return false;
        }
        if(book.getPublicationDate().equals(null) || book.getPublicationDate().equals("")) {
            return false;
        }
        return true;
    }

    private boolean isValidDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date.toString());
            return true;
        }
        catch(ParseException e){
            return false;
        }

    }



}
