package com.bazalytskyi.test.service;

import com.bazalytskyi.test.dao.IPublisherRepository;
import com.bazalytskyi.test.data.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IPublisherService {
    @Autowired
    IPublisherRepository repository;

    @Override
    public Publisher getPublisherById(int id) {
        return repository.getOne(id);
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return repository.save(publisher);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        if(repository.exists(publisher.getId())) {
            repository.save(publisher);
        }
    }

    @Override
    public void deletePublisher(int publisherId) {

    }

    @Override
    public Publisher getPublisherByUsername(String username) {
        return repository.getPublisherByUsername(username);
    }
}
