package epam.ermolaxe.rest.rest_service.repository;

import epam.ermolaxe.rest.rest_service.entity.Libraries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibrariesRepository extends JpaRepository<Libraries, Long> {
}
