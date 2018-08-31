package com.bazalytskyi.test.dao;

import com.bazalytskyi.test.data.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher getPublisherByUsername (String username);
}
