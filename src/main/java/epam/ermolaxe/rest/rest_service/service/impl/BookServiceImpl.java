package epam.ermolaxe.rest.rest_service.service.impl;

import epam.ermolaxe.rest.rest_service.entity.Book;
import epam.ermolaxe.rest.rest_service.repository.BookRepository;
import epam.ermolaxe.rest.rest_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(Object obj) {
        bookRepository.save((Book) obj);
    }

    @Override
    public List getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Object getById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void delete(Object obj) {
        bookRepository.delete((Book) obj);
    }
}
