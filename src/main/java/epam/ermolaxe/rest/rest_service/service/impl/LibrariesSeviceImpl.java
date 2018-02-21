package epam.ermolaxe.rest.rest_service.service.impl;

import epam.ermolaxe.rest.rest_service.entity.Libraries;
import epam.ermolaxe.rest.rest_service.repository.LibrariesRepository;
import epam.ermolaxe.rest.rest_service.service.LibrariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrariesSeviceImpl implements LibrariesService {

    @Autowired
    private LibrariesRepository librariesRepository;

    @Override
    public void save(Object obj) {
        librariesRepository.save((Libraries) obj);
    }

    @Override
    public List getAll() {
        return librariesRepository.findAll();
    }

    @Override
    public Object getById(Long id) {
        return librariesRepository.findOne(id);
    }

    @Override
    public void delete(Object obj) {
        librariesRepository.delete((Libraries) obj);
    }
}
