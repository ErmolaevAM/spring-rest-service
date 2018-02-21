package epam.ermolaxe.rest.rest_service.controller.restcontollers;

import epam.ermolaxe.rest.rest_service.entity.Libraries;
import epam.ermolaxe.rest.rest_service.service.LibrariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/libraries")
public class LibrariesRestController {

    @Autowired
    private LibrariesService librariesService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List> getAllLibraries() {
        List libraries = librariesService.getAll();
        if (libraries.isEmpty()) {
            return new ResponseEntity<>(libraries, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLibraryById(@PathVariable(value = "id") String id) {
        Libraries library = (Libraries) librariesService.getById(Long.valueOf(id));
        if (library == null) {
            return new ResponseEntity<>(String.format("library with id = %s is not found.", id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveLibrary(@RequestBody Libraries library) {
        Libraries lib = (Libraries) librariesService.getById(library.getId());
        if (lib != null) {
            librariesService.save(library);
            return new ResponseEntity<>(String.format("library with title = %s updated", library.getTitle()), HttpStatus.OK);
        } else {
            librariesService.save(library);
            return new ResponseEntity<>(String.format("library with title = %s saved", library.getTitle()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteLibrary(@RequestBody Libraries library) {
        Libraries lib = (Libraries) librariesService.getById(library.getId());
        if (lib != null) {
            librariesService.delete(library);
            return new ResponseEntity<>(String.format("library with title = %s deleted", library.getTitle()), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("cannot delete library with title = %s. is not existed", library.getTitle()), HttpStatus.NOT_FOUND);
    }
}
