package epam.ermolaxe.rest.rest_service.controller.restcontollers;

import epam.ermolaxe.rest.rest_service.entity.Author;
import epam.ermolaxe.rest.rest_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public ResponseEntity<List> getAllAuthors() {
        List authors = authorService.getAll();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(authors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorById(@PathVariable(value = "id") Long id) {
        Author author = (Author) authorService.getById(id);
        if (author == null) {
            return new ResponseEntity<>(String.format("cannot get author with id = %s. is not found.", id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public ResponseEntity<?> saveAuthor(@RequestBody Author author) {
        Author authodById = (Author) authorService.getById(author.getId());
        if (authodById != null) {
            return new ResponseEntity<>(String.format("author with name = %s is already exist.", author.getName()), HttpStatus.OK);
        } else {
            authorService.save(author);
            return new ResponseEntity<>(String.format("author with name = %s saved.", author.getName()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/author", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAuthor(@RequestBody Author author) {
        Author authodById = (Author) authorService.getById(author.getId());
        if (authodById != null) {
            authorService.save(author);
            return new ResponseEntity<>(String.format("author with name = %s updated.", author.getName()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("author with name = %s is not found.", author.getName()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLibrary(@PathVariable(value = "id")Long id) {
        Author authorById = (Author) authorService.getById(id);
        if (authorById == null) {
            return new ResponseEntity<>(String.format("cannot delete author with id = %s. is not found.", id), HttpStatus.NOT_FOUND);
        }
        authorService.delete(authorById);
        return new ResponseEntity<>(String.format("author with id = %s deleted.", id), HttpStatus.OK);
    }

}
