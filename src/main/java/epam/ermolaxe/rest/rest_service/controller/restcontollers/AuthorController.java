package epam.ermolaxe.rest.rest_service.controller.restcontollers;

import epam.ermolaxe.rest.rest_service.entity.Author;
import epam.ermolaxe.rest.rest_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List> getAllAuthors() {
        List authors = authorService.getAll();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(authors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorById(@PathVariable(value = "id") String id) {
        Author author = (Author) authorService.getById(Long.valueOf(id));
        if (author == null) {
            return new ResponseEntity<>(String.format("author with id = %s is not found.", id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveAuthor(@RequestBody Author author) {
        authorService.save(author);
        return new ResponseEntity<>(String.format("author with name = %s saved", author.getName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteLibrary(@RequestBody Author author) {
        Author authorById = (Author) authorService.getById(author.getId());
        if (author.equals(authorById)) {
            authorService.delete(author);
            return new ResponseEntity<>(String.format("author with name = %s deleted", author.getName()), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("cannot delete author with name = %s. is not existed", author.getName()), HttpStatus.NOT_FOUND);
    }

}
