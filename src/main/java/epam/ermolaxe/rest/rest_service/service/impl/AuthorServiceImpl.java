package epam.ermolaxe.rest.rest_service.service.impl;

import epam.ermolaxe.rest.rest_service.entity.Author;
import epam.ermolaxe.rest.rest_service.repository.AuthorRepository;
import epam.ermolaxe.rest.rest_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(Object obj) {
        authorRepository.save((Author) obj);
    }

    @Override
    public List getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Object getById(Long id) {
        return authorRepository.findOne(id);
    }

    @Override
    public void delete(Object obj) {
        authorRepository.delete((Author) obj);
    }
}
