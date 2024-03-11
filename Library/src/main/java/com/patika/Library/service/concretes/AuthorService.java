package com.patika.Library.service.concretes;

import com.patika.Library.core.exception.NotFoundException;
import com.patika.Library.core.utilies.Message;
import com.patika.Library.entity.Author;
import com.patika.Library.repository.AuthorRepository;
import com.patika.Library.service.abstracts.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author get(long id) {

        return this.authorRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.authorRepository.findAll(pageable);
    }

     @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.authorRepository.save(author);
    }

    @Override
    public boolean delete(long id) {
        Author author = this.get(id);
        this.authorRepository.delete(author);
        return true;
    }
}