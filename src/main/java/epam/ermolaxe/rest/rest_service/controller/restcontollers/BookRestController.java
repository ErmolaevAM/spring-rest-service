package epam.ermolaxe.rest.rest_service.controller.restcontollers;

import epam.ermolaxe.rest.rest_service.entity.Book;
import epam.ermolaxe.rest.rest_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List> getAllBooks() {
        List books = bookService.getAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(books, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookById(@PathVariable(value = "id") String id) {
        Book book = (Book) bookService.getById(Long.valueOf(id));
        if (book == null) {
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveBook1(@RequestBody Book book) {
        Book bookById = (Book) bookService.getById(book.getId());
        if (bookById != null) {
            bookService.save(book);
            return new ResponseEntity<>(String.format("book with title = %s updated", book.getTitle()), HttpStatus.OK);
        } else {
            bookService.save(book);
            return new ResponseEntity<>(String.format("book with title = %s saved", book.getTitle()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteBook(@RequestBody Book book) {
        Book bookById = (Book) bookService.getById(book.getId());
        if (bookById != null) {
            bookService.delete(book);
            return new ResponseEntity<>(String.format("book with title = %s deleted", book.getTitle()), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("cannot delete book with title = %s. is not existed", book.getTitle()), HttpStatus.NOT_FOUND);
    }
}
