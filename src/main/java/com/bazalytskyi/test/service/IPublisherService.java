package com.bazalytskyi.test.service;

import com.bazalytskyi.test.data.entity.Publisher;

import java.util.List;

public interface IPublisherService {

	Publisher getPublisherById(int id);

	Publisher addPublisher(Publisher publisher);

	void updatePublisher(Publisher publisher);

	void deletePublisher(int publisherId);

    Publisher getPublisherByUsername(String username);
}
