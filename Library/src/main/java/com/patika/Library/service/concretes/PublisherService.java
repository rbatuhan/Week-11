package com.patika.Library.service.concretes;

import com.patika.Library.core.exception.NotFoundException;
import com.patika.Library.core.utilies.Message;
import com.patika.Library.entity.Publisher;
import com.patika.Library.repository.PublisherRepository;
import com.patika.Library.service.abstracts.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IPublisherService {
    @Autowired
    private PublisherRepository publisherRepository;
    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepository.save(publisher);
    }

    @Override
    public Publisher get(long id) {
        return this.publisherRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.publisherRepository.findAll(pageable);
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get(publisher.getId());
        return this.publisherRepository.save(publisher);
    }

    @Override
    public boolean delete(long id) {
        Publisher publisher = this.get(id);
        this.publisherRepository.delete(publisher);
        return true;
    }
}
