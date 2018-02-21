package epam.ermolaxe.rest.rest_service.service;

import java.util.List;

public interface AbsractFinder {

    void save(Object obj);

    List getAll();

    Object getById(Long id);

    void delete(Object obj);
}
