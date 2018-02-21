package epam.ermolaxe.rest.rest_service.repository;

import epam.ermolaxe.rest.rest_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
