package epam.ermolaxe.rest.rest_service.controller.restcontollers;

import epam.ermolaxe.rest.rest_service.entity.Libraries;
import epam.ermolaxe.rest.rest_service.service.LibrariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class LibrariesRestController {

    @Autowired
    private LibrariesService librariesService;

    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public ResponseEntity<List> getAllLibraries() {
        List libraries = librariesService.getAll();
        if (libraries.isEmpty()) {
            return new ResponseEntity<>(libraries, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    @RequestMapping(value = "/library/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLibraryById(@PathVariable(value = "id") Long id) {
        Libraries library = (Libraries) librariesService.getById(id);
        if (library == null) {
            return new ResponseEntity<>(String.format("library with id = %s is not found.", id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @RequestMapping(value = "/library", method = RequestMethod.POST)
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

    @RequestMapping(value = "/library/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteLibrary(@PathVariable(value = "id") Long id) {
        Libraries lib = (Libraries) librariesService.getById(id);
        if (lib == null) {
            return new ResponseEntity<>(String.format("cannot delete library with id = %s. is not existed", id), HttpStatus.NOT_FOUND);
        }
        librariesService.delete(lib);
        return new ResponseEntity<>(String.format("library with id = %s deleted", id), HttpStatus.OK);
    }
}
