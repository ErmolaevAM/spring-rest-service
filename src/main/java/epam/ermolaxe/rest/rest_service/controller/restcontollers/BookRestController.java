package epam.ermolaxe.rest.rest_service.controller.restcontollers;

import epam.ermolaxe.rest.rest_service.entity.Book;
import epam.ermolaxe.rest.rest_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<List> getAllBooks() {
        List books = bookService.getAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(books, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookById(@PathVariable(value = "id") Long id) {
        Book book = (Book) bookService.getById(id);
        if (book == null) {
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        Book bookById = (Book) bookService.getById(book.getId());
        if (bookById != null) {
            return new ResponseEntity<>(String.format("book with title = %s is already exist.", book.getTitle()), HttpStatus.OK);
        } else {
            bookService.save(book);
            return new ResponseEntity<>(String.format("book with title = %s saved", book.getTitle()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        Book bookById = (Book) bookService.getById(book.getId());
        if (bookById != null) {
            bookService.save(book);
            return new ResponseEntity<>(String.format("book with title = %s updated.", book.getTitle()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("book with title = %s is not found.", book.getTitle()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) {
        Book bookById = (Book) bookService.getById(id);
        if (bookById == null) {
            return new ResponseEntity<>(String.format("cannot delete book with id = %s. is not existed.", id), HttpStatus.NOT_FOUND);
        }
        bookService.delete(bookById);
        return new ResponseEntity<>(String.format("book with id = %s deleted.", id), HttpStatus.OK);
    }
}
