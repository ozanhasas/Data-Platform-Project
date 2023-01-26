package com.example.tezspringbe.repos;

import com.example.tezspringbe.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author meteh
 * @create 2.05.2022 18:53
 */
public interface ContactRepo  extends MongoRepository<Contact,String> {
}
